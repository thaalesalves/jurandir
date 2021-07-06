package es.thalesalv.jurandir.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JurandirConfig {
    
    @JsonProperty("gpt")
    private GPT gpt;

    @JsonProperty("jurandir")
    private Jurandir jurandir;
}
