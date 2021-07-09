package es.thalesalv.jurandir.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GPT {

    @JsonProperty("temp")
    private double temperature;

    @JsonProperty("prob_value")
    private double probabilityValue;

    @JsonProperty("rep_pen")
    private double repetitionPenalty;

    @JsonProperty("max_tokens")
    private int maxTokens;

    @JsonProperty("min_tokens")
    private int minTokens;

    @JsonProperty("num_seq")
    private int numberOfSequences;

    @JsonProperty("server_address")
    private String serverAddress;
}