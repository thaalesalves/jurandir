package es.thalesalv.jurandir.domain.model;

import java.util.List;
import java.util.UUID;

import es.thalesalv.jurandir.adapter.data.entity.ContextEntryEntity;
import es.thalesalv.jurandir.adapter.data.entity.ScriptEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Scenario {
    
    private UUID id;
    private String title;
    private List<ScriptEntity> scripts;
    private List<ContextEntryEntity> contextEntries;
}
