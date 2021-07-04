package es.thalesalv.jurandir.adapter.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import es.thalesalv.jurandir.adapter.model.KoboldAiRequestBody;
import es.thalesalv.jurandir.application.datastore.ContextDatastore;
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

    @Value("${jurandir.gpt.gpt-api}")
    private String gptApi;

    private final WebClient webClient;
    private final ContextDatastore contextDatastore;
    private final ObjectMapper objectMapper;

    public GPTService(WebClient.Builder webClientBuilder, ContextDatastore contextDatastore, ObjectMapper objectMapper) {
        this.contextDatastore = contextDatastore;
        this.objectMapper = objectMapper;

        contextDatastore.setBotUrl(gptApi);
        this.webClient = webClientBuilder.baseUrl("http://0afe4c13cd08.ngrok.io").build();
    }

    public Mono<String> prompt(String context, String message, User author) throws JsonProcessingException {

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
                        .append(context.replace("Grand Prognosticator", "You"))
                        .append(author.getName().replace("Grand Prognosticator", "You") + " says: " + message)
                        .append("\nYou reply: ")
                        .toString())
                    .build()))
            .retrieve()
            .bodyToMono(String.class)
            .map(reply -> reply.split("\n")[0]);
    }
}
