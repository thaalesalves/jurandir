package es.thalesalv.jurandir.domain.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContextEntry {
    
    private UUID id;
    private String key;
    private String entry;
    private boolean isFixed;
    private UUID scenarioId;
}
