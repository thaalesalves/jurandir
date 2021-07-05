package es.thalesalv.jurandir.adapter.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import es.thalesalv.jurandir.adapter.model.KoboldAiRequestBody;
import net.dv8tion.jda.api.entities.User;
import reactor.core.publisher.Mono;

@Component
public class GPTService {

    @Value("${jurandir.gpt.temperature}")
    private double temperature;

    @Value("${jurandir.gpt.p-value}")
    private double pValue;

    @Value("${jurandir.gpt.min-tokens}")
    private int minTokens;
    
    @Value("${jurandir.gpt.max-tokens}")
    private int maxTokens;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public GPTService(@Value("${jurandir.gpt.gpt-api}") String gptApi, WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder.baseUrl(gptApi).build();
    }

    public Mono<String> prompt(User bot, String context, String message, User author) throws JsonProcessingException {

        return this.webClient.post()
            .uri(uri -> uri.pathSegment("request").build())
            .headers(headers -> {
                headers.add("Content-Type", "application/json");
                headers.add("Accept", "*/*");
                headers.add("Connection", "keep-alive");
            })
            .bodyValue(objectMapper.writeValueAsString(KoboldAiRequestBody.builder()
                    .temperature(temperature)
                    .topP(pValue)
                    .min(minTokens)
                    .max(maxTokens)
                    .numseqs(1)
                    .repPen(1)
                    .text(new StringBuilder()
                        .append(context)
                        .append(author.getName().replace(bot.getName(), "You") + " says: " + message.replace(bot.getAsTag(), "").trim())
                        .append("\nJurandir replies: ")
                        .toString())
                    .build()))
            .retrieve()
            .bodyToMono(String.class);
    }
}
