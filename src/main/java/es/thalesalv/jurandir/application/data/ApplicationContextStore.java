package es.thalesalv.jurandir.application.data;

import es.thalesalv.jurandir.domain.model.bot.JurandirConfig;

public interface ApplicationContextStore {

    public JurandirConfig getConfig();

    public void setConfig(JurandirConfig config);

    public void clearContext();
}
