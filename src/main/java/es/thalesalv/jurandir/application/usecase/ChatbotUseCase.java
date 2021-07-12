package es.thalesalv.jurandir.application.usecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import es.thalesalv.jurandir.adapter.controller.client.GPTChatBotService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Component
@RequiredArgsConstructor
public class ChatbotUseCase {

    @Value("${jurandir.discord.context-window}")
    private int contextWindow;

    private final ObjectMapper objectMapper;
    private final GPTChatBotService gpt;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatbotUseCase.class);

    public void generateOutput(MessageReceivedEvent event, User author, User bot, String message) {

        TextChannel channel = event.getTextChannel();
        MessageHistory.getHistoryBefore(channel, event.getMessage().getId())
            .limit(contextWindow)
            .queue(msgHist -> {
                try {
                    StringBuilder sb = new StringBuilder();
                    List<Message> messages = new ArrayList<>(msgHist.getRetrievedHistory());
                    Collections.reverse(messages);
                    messages.forEach(msg -> {
                        sb.append(msg.getAuthor().getName().trim())
                                .append(" says: ")
                                .append(msg.getContentRaw()
                                        .replaceAll("<@!*&*[0-9]+>", StringUtils.EMPTY).replace(bot.getName(), "You").trim())
                                .append("\n");
                    });

                    gpt.prompt(bot, sb.toString(), message, author).map(gptResponse -> {
                        try {
                            String formattedResponse = StringUtils.EMPTY;
                            JsonNode data = objectMapper.readValue(gptResponse, ObjectNode.class).get("data");

                            if (data.has("seqs")) {
                                formattedResponse = data.get("seqs").path(0).asText().split("\n")[0].trim().replaceAll("^\"|\"$", StringUtils.EMPTY);
                            } else if (data.has("text")) {
                                formattedResponse = data.get("text").asText().split("\n")[0].trim().replaceAll("^\"|\"$", StringUtils.EMPTY);
                            } else {
                                throw new RuntimeException("The JSON response is not in the correct format.");
                            }

                            LOGGER.info("GPT model response -> {}", formattedResponse);
                            if (StringUtils.isBlank(formattedResponse)) {
                                formattedResponse = "Sorry, I don't know what to say.";
                            }

                            channel.sendMessage(formattedResponse).complete();
                        } catch (Exception e) {
                            LOGGER.error(e.getMessage());
                            throw new RuntimeException("Error during AI output generation.", e);
                        }

                        return null;
                    }).subscribe();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException("Error parsing AI response.", e);
            }
        });
    }
}
