package es.thalesalv.jurandir.application.bean;

import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Configuration;

import es.thalesalv.jurandir.application.data.ApplicationContextStore;
import es.thalesalv.jurandir.domain.exception.JurandirConfigException;
import es.thalesalv.jurandir.domain.model.bot.JurandirConfig;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class JurandirConfigBean {

    private static final String CONFIG_FILE_NAME = "config.json";

    private final ObjectMapper objectMapper;
    private final ApplicationContextStore contextStore;

    public String updateConfig(String stringJson) {
        try {
            JurandirConfig config = objectMapper.readValue(stringJson, JurandirConfig.class);
            contextStore.setConfig(config);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(CONFIG_FILE_NAME).toFile(), stringJson);
            return stringJson;
        } catch (Exception error) {
            throw new JurandirConfigException("Error updating config file through json string", error);
        }
    }

    public String updateConfig() {
        try {
            JurandirConfig config = contextStore.getConfig();
            String configFile = objectMapper.writeValueAsString(config);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(CONFIG_FILE_NAME).toFile(), config);
            return configFile;
        } catch (Exception error) {
            throw new JurandirConfigException("Error updating config file", error);
        }
    }

    public String loadConfig() {
        try {
            String configFile = objectMapper.writeValueAsString(objectMapper.readValue(Paths.get(CONFIG_FILE_NAME).toFile(), Map.class));
            JurandirConfig config = objectMapper.readValue(configFile, JurandirConfig.class);
            contextStore.setConfig(config);
            return configFile;
        } catch (Exception error) {
            throw new JurandirConfigException("Error loading config file", error);
        }
    }
}
