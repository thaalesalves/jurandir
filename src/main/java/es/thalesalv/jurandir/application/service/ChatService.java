package es.thalesalv.jurandir.application.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import es.thalesalv.jurandir.application.discord.commands.GenericFunction;
import es.thalesalv.jurandir.application.usecase.ChatbotUseCase;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
@RequiredArgsConstructor
public class ChatService extends ListenerAdapter {

    private static final String CHAT_LOGGER = "[{}] {} said @ {}: {}";
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    @Value("${jurandir.discord.api.id}")
    private String botId;

    @Value("${jurandir.discord.message.operator}")
    private String defaultOperator;

    private final BeanFactory factory;
    private final ChatbotUseCase chatbot;
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            final Message originalMessage = event.getMessage();
            final MessageChannel channel = event.getChannel();
            final Guild guild = event.getGuild();
            final User bot = guild.getMemberById(botId).getUser();
            final User author = event.getAuthor();
            final String message = originalMessage.getContentDisplay().trim();

            final String operator = message.split(StringUtils.SPACE)[0];
            final boolean isElegible = event.isFromType(ChannelType.TEXT) && 
                    (!author.isBot() || originalMessage.getMentionedUsers().contains(bot));

            LOGGER.info(CHAT_LOGGER, guild.getName(), author.getName(), channel.getName(), event.getMessage().getContentRaw());

            if (isElegible) {
                channel.sendTyping().complete();
                final String filteredMessage = message.replace("@" + bot.getName(), StringUtils.EMPTY).trim();
                if (!operator.equals(defaultOperator)) {
                    chatbot.generateOutput(event, author, bot, filteredMessage);
                } else {
                    final String command = filteredMessage.replace(operator, StringUtils.EMPTY).trim().split(StringUtils.SPACE)[0];
                    final String[] params = Optional.ofNullable(filteredMessage.replace(operator, StringUtils.EMPTY)
                            .replace(command, StringUtils.EMPTY).split(StringUtils.SPACE)).orElse(new String[0]);

                    try {
                        GenericFunction function = (GenericFunction) factory.getBean(StringUtils.capitalize(command) + "Command");
                        function.execute(command, params);
                        return;
                    } catch (BeansException e) {
                        LOGGER.debug("The command request does not exist: " + command, e);
                        channel.sendMessage("Invalid command: " + command).complete();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Error while dealing with user message.", e);
        }
    }
}
