package es.thalesalv.jurandir.application.data;

import org.springframework.stereotype.Component;

import es.thalesalv.jurandir.domain.model.JurandirConfig;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationContextStoreImpl implements ApplicationContextStore {

    private ThreadLocal<JurandirConfig> config = new ThreadLocal<JurandirConfig>();

    @Override
    public JurandirConfig getConfig() {
        return config.get();
    }

    @Override
    public void setConfig(JurandirConfig config) {
        this.config.set(config);
    }

    @Override
    public void clearContext() {
        config.remove();
    }
}