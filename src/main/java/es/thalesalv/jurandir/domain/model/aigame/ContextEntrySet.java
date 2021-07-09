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
public class ContextEntrySet {

    private UUID id;
    private String name;
    private String description;
    private Set<Scenario> scenarios;
    private Set<ContextEntry> entities;
}
