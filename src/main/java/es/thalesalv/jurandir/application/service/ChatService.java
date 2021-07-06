package es.thalesalv.jurandir.application.service;

import javax.annotation.PreDestroy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
@RequiredArgsConstructor
public class ChatService extends ListenerAdapter {

    private Guild guild;

    private static final String CHAT_LOGGER = "[{}] {} said @ {}: {}";
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    private final ObjectMapper objectMapper;
    private final GPTService gpt;

    @Value("${jurandir.discord.api.id}")
    private String botId;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            guild = event.getGuild();
            User bot = guild.getMemberById(botId).getUser();
            MessageChannel channel = event.getChannel();
            User author = event.getAuthor();
            Message originalMessage = event.getMessage();
            String message = originalMessage.getContentDisplay()
                    .replace(event.getMessage().getMentionedUsers().get(event.getMessage().getMentionedUsers().indexOf(bot)).getAsMention(), StringUtils.EMPTY)
                    .replace("@" + bot.getName(), StringUtils.EMPTY).trim();

            boolean isElegible = event.isFromType(ChannelType.TEXT) && (event.getMessage().getMentionedUsers().contains(bot));
            LOGGER.info(CHAT_LOGGER, guild.getName(), author.getName(), channel.getName(), event.getMessage().getContentRaw());
            MessageHistory.getHistoryBefore(channel, event.getMessage().getId()).limit(10)
                .queue(msgHist -> {
                    try {
                        StringBuilder sb = new StringBuilder();
                        msgHist.getRetrievedHistory().forEach(msg -> {
                            try {
                                sb.insert(0, msg.getAuthor().getName().trim()
                                    + " says: "
                                    + msg.getContentRaw().replaceAll("<@!*&*[0-9]+>", StringUtils.EMPTY).replace(bot.getName(), "You").trim()
                                    +"\n");
                            } catch (Exception e) {
                                LOGGER.error(e.getMessage());
                                throw new RuntimeException("Error during parsing AI context.", e);
                            }
                        });

                        if (!author.isBot() && isElegible) {
                            gpt.prompt(bot, sb.toString(), message, author).map(gptResponse -> {
                                try {
                                    String formattedResponse = StringUtils.EMPTY;
                                    JsonNode node = objectMapper.readValue(gptResponse, ObjectNode.class).get("data");
                                    if (node.has("seqs")) {
                                        formattedResponse = node.get("seqs").path(0).asText().trim().split("\n")[0].trim().replaceAll("^\"|\"$", StringUtils.EMPTY);
                                    } else if (node.has("text")) {
                                        formattedResponse = node.get("text").asText().trim().split("\n")[0].trim().replaceAll("^\"|\"$", StringUtils.EMPTY);
                                    } else {
                                        throw new RuntimeException("The JSON response is not in the correct format.");
                                    }
        
                                    LOGGER.info("GPT model response -> {}", formattedResponse);
                                    if (formattedResponse.isBlank()) {
                                        formattedResponse = "Sorry, I don't know what to say.";
                                    }
        
                                    channel.sendMessage(formattedResponse.trim()).mentionRepliedUser(true).complete();
                                    return formattedResponse;
                                } catch (Exception e) {
                                    LOGGER.error(e.getMessage());
                                    throw new RuntimeException("Error parsing AI response.", e);
                                }
                            }).subscribe();
                        }
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                        throw new RuntimeException("Error parsing AI response.", e);
                    }
                });
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Error while dealing with user message.", e);
        }
    }

    @PreDestroy
    private void die() {
        LOGGER.debug("Killing chat service bean.");
        guild.getJDA().shutdown();
    }
}
