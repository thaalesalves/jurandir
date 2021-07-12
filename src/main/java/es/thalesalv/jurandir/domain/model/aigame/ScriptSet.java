package es.thalesalv.jurandir.domain.model.aigame;

import java.util.Set;
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
public class ScriptSet {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("context_modifier")
    private String contextModifier;

    @JsonProperty("input_modifier")
    private String inputModifier;

    @JsonProperty("output_modifier")
    private String outputModifier;

    @JsonProperty("shared_library")
    private String sharedLibrary;

    @JsonProperty("scenarios")
    private Set<Scenario> scenarios;
}
