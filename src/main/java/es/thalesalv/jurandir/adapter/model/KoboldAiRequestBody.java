package es.thalesalv.jurandir.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KoboldAiRequestBody {

    @JsonProperty("text")
    private String text;

    @JsonProperty("min")
    private int min;

    @JsonProperty("max")
    private int max;

    @JsonProperty("rep_pen")
    private double repPen;

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("top_p")
    private double topP;

    @JsonProperty("numseqs")
    private int numseqs;
}
