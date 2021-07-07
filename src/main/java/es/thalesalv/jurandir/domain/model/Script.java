package es.thalesalv.jurandir.domain.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Script {

    private UUID id;
    private UUID scenarioId;
    private String name;
    private String code;
}
