package es.thalesalv.jurandir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import es.thalesalv.jurandir.application.service.ChatService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@SpringBootApplication
public class JurandirApplication {
    private static String API_TOKEN;
    private static ChatService CHAT_SERVICE;

    @Autowired
    private void setChatService(ChatService chatService) {
        CHAT_SERVICE = chatService;
    }

    @Value("${jurandir.discord.api.token}")
    private void setApiToken(String apiToken) {
        API_TOKEN = apiToken;
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(JurandirApplication.class, args);
        JDA jda = JDABuilder.createDefault(API_TOKEN)
            .addEventListeners(CHAT_SERVICE)
            .build();

        ctx.getBeanFactory().registerSingleton("JDA", jda);
    }
}
