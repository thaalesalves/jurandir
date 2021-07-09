package es.thalesalv.jurandir.domain.model.aigame;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScriptSet {

    private UUID id;
    private String title;
    private String contextModifier;
    private String inputModifier;
    private String outputModifier;
    private String sharedLibrary;
    private Set<Scenario> scenarios;
}
