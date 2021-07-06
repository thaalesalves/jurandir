package es.thalesalv.jurandir.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import es.thalesalv.jurandir.adapter.model.KoboldAiRequestBody;
import es.thalesalv.jurandir.application.data.ApplicationContextStore;
import net.dv8tion.jda.api.entities.User;
import reactor.core.publisher.Mono;

@Service
public class GPTService {

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final ApplicationContextStore contextStore;

    public GPTService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper,
            ApplicationContextStore contextStore) {

        this.contextStore = contextStore;
        this.objectMapper = objectMapper;
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> prompt(User bot, String context, String message, User author) throws JsonProcessingException {

        return this.webClientBuilder.baseUrl(contextStore.getConfig().getGpt().getServerAddress())
            .build()
            .post()
            .uri(uri -> uri.pathSegment("request").build())
            .headers(headers -> {
                headers.add("Content-Type", "application/json");
                headers.add("Accept", "*/*");
                headers.add("Connection", "keep-alive");
            })
            .bodyValue(objectMapper.writeValueAsString(KoboldAiRequestBody.builder()
                    .temperature(contextStore.getConfig().getGpt().getTemperature())
                    .topP(contextStore.getConfig().getGpt().getProbabilityValue())
                    .min(contextStore.getConfig().getGpt().getMinTokens())
                    .max(contextStore.getConfig().getGpt().getMaxTokens())
                    .numseqs(contextStore.getConfig().getGpt().getNumberOfSequences())
                    .repPen(contextStore.getConfig().getGpt().getRepetitionPenalty())
                    .text(new StringBuilder()
                            .append(context)
                            .append(author.getName().replace(bot.getName(), "You"))
                            .append(" says: ")
                            .append(message.replace(bot.getAsTag(), StringUtils.EMPTY).trim())
                            .append("\nJurandir replies: ")
                            .toString())
                    .build()))
            .retrieve()
            .bodyToMono(String.class);
    }
}
