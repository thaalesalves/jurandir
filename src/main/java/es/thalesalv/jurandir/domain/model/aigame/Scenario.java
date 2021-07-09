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
public class Scenario {

    private UUID id;
    private String title;
    private ScriptSet scriptSet;
    private Set<ContextEntrySet> contextEntries;
    private Set<Story> stories;
}
