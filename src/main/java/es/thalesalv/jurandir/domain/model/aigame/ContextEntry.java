package es.thalesalv.jurandir.domain.model.aigame;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContextEntry {

    private UUID id;
    private String key;
    private String entry;
    private boolean isFixed;
}
