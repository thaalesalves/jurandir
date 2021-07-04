package es.thalesalv.jurandir.application.datastore;

import org.springframework.stereotype.Component;

@Component
public class ContextDatastore {
    
    private ThreadLocal<String> botUrl = new ThreadLocal<String>();

    public void setBotUrl(String botUrl) {
        this.botUrl.set(botUrl);
    }

    public String getBotUrl() {
        return this.botUrl.get();
    }

    public void clearContext() {
        botUrl.remove();
    }
}
