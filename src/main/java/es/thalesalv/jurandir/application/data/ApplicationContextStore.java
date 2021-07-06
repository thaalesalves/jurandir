package es.thalesalv.jurandir.application.data;

import es.thalesalv.jurandir.domain.model.JurandirConfig;

public interface ApplicationContextStore {

    public JurandirConfig getConfig();

    public void setConfig(JurandirConfig config);

    public void clearContext();
}
