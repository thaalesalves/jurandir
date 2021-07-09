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
public class Story {

    private UUID id;
    private String title;
    private String description;
    private String prompt;
    private Scenario scenario;
}
