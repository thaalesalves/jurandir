package es.thalesalv.jurandir.application.datastore;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.JDA;

@Component
public class ContextDatastore {
    
    private ThreadLocal<String> botUrl = new ThreadLocal<String>();
    private ThreadLocal<JDA> jda = new ThreadLocal<JDA>();

    public void setBotUrl(String botUrl) {
        this.botUrl.set(botUrl);
    }

    public String getBotUrl() {
        return this.botUrl.get();
    }

    public void setJda(JDA jda) {
        this.jda.set(jda);
    }

    public JDA getJda() {
        return this.jda.get();
    }

    public void clearContext() {
        botUrl.remove();
        jda.remove();
    }
}
