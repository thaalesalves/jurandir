package es.thalesalv.jurandir.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import es.thalesalv.jurandir.application.bean.JurandirConfigBean;
import es.thalesalv.jurandir.domain.model.bot.JurandirConfig;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JurandirConfigService {

    private final ObjectMapper objectMapper;
    private final JurandirConfigBean configBean;

    public JurandirConfig updateConfig(JurandirConfig config) throws JsonProcessingException {
        configBean.updateConfig(objectMapper.writeValueAsString(config));
        return config;
    }
}
