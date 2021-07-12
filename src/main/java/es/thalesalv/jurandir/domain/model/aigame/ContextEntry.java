package es.thalesalv.jurandir.domain.model.aigame;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContextEntry {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("key")
    private String key;

    @JsonProperty("entry")
    private String entry;

    @JsonProperty("is_fixed")
    private boolean isFixed;
}
