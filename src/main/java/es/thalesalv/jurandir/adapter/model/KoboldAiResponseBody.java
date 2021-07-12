package es.thalesalv.jurandir.adapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KoboldAiResponseBody {

    @JsonProperty("data")
    private KoboldAiResponseBodyData data;
}
