package es.thalesalv.jurandir.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import es.thalesalv.jurandir.application.bean.JurandirConfigBean;
import es.thalesalv.jurandir.application.data.ApplicationContextStore;
import es.thalesalv.jurandir.domain.model.bot.JurandirConfig;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JurandirConfigService {

    private final ObjectMapper objectMapper;
    private final JurandirConfigBean configBean;
    private final ApplicationContextStore contextStore;

    public JurandirConfig updateConfig(JurandirConfig config) throws JsonProcessingException {
        contextStore.setConfig(config);
        configBean.updateConfig(objectMapper.writeValueAsString(config));
        return config;
    }
}
