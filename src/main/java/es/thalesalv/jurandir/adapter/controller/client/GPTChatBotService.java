package es.thalesalv.jurandir.adapter.controller.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import es.thalesalv.jurandir.adapter.model.KoboldAiRequestBody;
import es.thalesalv.jurandir.application.bean.JurandirConfigBean;
import es.thalesalv.jurandir.domain.model.bot.JurandirConfig;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.User;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GPTChatBotService {

    private final ObjectMapper objectMapper;
    private final JurandirConfigBean configBean;
    private final WebClient.Builder webClientBuilder;

    public Mono<String> prompt(User bot, String context, String message, User author) throws JsonProcessingException {

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
            .bodyValue(objectMapper.writeValueAsString(KoboldAiRequestBody.builder()
                    .temperature(config.getGpt().getTemperature())
                    .topP(config.getGpt().getProbabilityValue())
                    .min(config.getGpt().getMinTokens())
                    .max(config.getGpt().getMaxTokens())
                    .numseqs(config.getGpt().getNumberOfSequences())
                    .repPen(config.getGpt().getRepetitionPenalty())
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
