package es.thalesalv.jurandir.adapter.controller.client;

import java.io.FileReader;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;

import com.eclipsesource.v8.NodeJS;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import es.thalesalv.jurandir.adapter.data.entity.ContextEntryEntity;
import es.thalesalv.jurandir.adapter.data.repository.ContextEntryRepository;
import es.thalesalv.jurandir.adapter.model.KoboldAiRequestBody;
import es.thalesalv.jurandir.adapter.model.KoboldAiResponseBody;
import es.thalesalv.jurandir.application.bean.JurandirConfigBean;
import es.thalesalv.jurandir.application.mapper.JurandirObjectMapper;
import es.thalesalv.jurandir.domain.exception.GPTInferenceException;
import es.thalesalv.jurandir.domain.model.aigame.ContextEntry;
import es.thalesalv.jurandir.domain.model.aigame.StoryPrompt;
import es.thalesalv.jurandir.domain.model.bot.JurandirConfig;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GPTStoryInferenceService {

    private final JDA jda;
    private final ObjectMapper objectMapper;
    private final ScriptEngine scriptEngine;
    private final Bindings scriptEngineBindings;
    private final JurandirConfigBean configBean;
    private final WebClient.Builder webClientBuilder;
    private final ContextEntryRepository contextEntryRepository;
    private final JurandirObjectMapper<ContextEntryEntity, ContextEntry> contextEntryMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(GPTStoryInferenceService.class);

    public Mono<KoboldAiResponseBody> prompt(StoryPrompt prompt) {

        JurandirConfig config = configBean.loadConfig();
        return this.webClientBuilder.baseUrl(config.getGpt().getServerAddress())
            .build()
            .post()
            .uri(uri -> uri.pathSegment("request").build())
            .headers(headers -> {
                headers.add("Content-Type", "application/json");
                headers.add("Accept", "*/*");
                headers.add("Connection", "keep-alive");
            })
            .bodyValue(this.formatRequestBody(prompt))
            .retrieve()
            .bodyToMono(KoboldAiResponseBody.class)
            .map(response -> {
                Stream.of(response.getData().getSequences().get(0))
                        .map(inference -> {
                            try {
                                scriptEngineBindings.put("inference", inference);
                                String result = (String) scriptEngine.eval("outputModifier.modifier(inference);",
                                        scriptEngineBindings);

                                jda.getGuildById(prompt.getGuildId()).getTextChannelById(prompt.getChannelId()).sendMessage(result).complete();
                                return result;
                            } catch (Exception e) {
                                throw new GPTInferenceException("Error applying output modifier to model inference", e);
                            }
                        });
                return response;
            });
    }

    private String formatRequestBody(StoryPrompt prompt) {
        try {
            LOGGER.debug("Started request body conversion -> {}", prompt);
            StoryPrompt modifiedPrompt = Optional.ofNullable(prompt)
                    .map(p -> {
                        try {
                            Set<ContextEntry> contextEntries = contextEntryMapper.map(contextEntryRepository.findAll());

                            final NodeJS node = NodeJS.createNodeJS();
                            scriptEngineBindings.put("contextEntries", !contextEntries.isEmpty() ? contextEntries : p.getContextEntries());

                            scriptEngine.eval(new FileReader("scripts/inputModifier.js"));
                            scriptEngine.eval(new FileReader("scripts/shared.js"));
                            scriptEngine.eval("state = { worldInfo: contextEntries };", scriptEngineBindings);

                            Invocable invocable = (Invocable) scriptEngine;
                            Object result = invocable.invokeFunction("modifier", p.getPrompt());
                            // p.setPrompt(result);
                            return p;
                        } catch (Exception e) {
                            throw new GPTInferenceException("An error occured while formatting or applying scripts to story input.", e);
                        }
                    })
                    .map(p -> {
                        try {
                            scriptEngineBindings.put("sharedLibrary", p.getScriptSet().getSharedLibrary());
                            scriptEngineBindings.put("contextModifier", prompt.getScriptSet().getContextModifier());
                            scriptEngineBindings.put("text", p.getPrompt());
                            String result = (String) scriptEngine.eval("", scriptEngineBindings);
                            LOGGER.info("LMI -> {}", result);
                            p.setPrompt(result);
                            return p;
                        } catch (Exception e) {
                            throw new GPTInferenceException("An error occured while formatting or applying scripts to story input.", e);
                        }
                    })
                    .orElseThrow(() -> new GPTInferenceException("Error generating output from the model. THe input cannot be null."));

            return objectMapper.writeValueAsString(KoboldAiRequestBody.builder()
                    .text(modifiedPrompt.getPrompt())
                    .numseqs(modifiedPrompt.getGpt().getNumberOfSequences())
                    .repPen(modifiedPrompt.getGpt().getRepetitionPenalty())
                    .temperature(modifiedPrompt.getGpt().getTemperature())
                    .topP(modifiedPrompt.getGpt().getProbabilityValue())
                    .build());
        } catch (Exception e) {
            LOGGER.error("An error occured while processing model inference -> {}", e);
            throw new RuntimeException(e);
        }
    }
}
