package es.thalesalv.jurandir.domain.model.bot;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.thalesalv.jurandir.domain.model.GPT;
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
