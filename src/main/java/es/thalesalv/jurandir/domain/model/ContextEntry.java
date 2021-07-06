package es.thalesalv.jurandir.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContextEntry {
    
    private List<String> keys;
    private String entry;

    @JsonProperty("is_fixed")
    private boolean isFixed;
}
