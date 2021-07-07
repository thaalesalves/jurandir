package es.thalesalv.jurandir.adapter.bean;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import es.thalesalv.jurandir.application.bean.JurandirConfigBean;
import es.thalesalv.jurandir.application.service.ChatService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Component
@RequiredArgsConstructor
public class DiscordConfigurationBean {

    @Value("${jurandir.discord.api.token}")
    private String apiToken;

    private final ChatService chatService;
    private final JurandirConfigBean jurandirConfigBean;

    @Bean
    public JDA jda() throws LoginException {

        jurandirConfigBean.updateConfig();
        return JDABuilder.createDefault(apiToken)
            .addEventListeners(chatService)
            .build();
    }
}
