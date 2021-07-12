package es.thalesalv.jurandir.adapter.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KoboldAiResponseBodyData {

    @JsonProperty("seqs")
    private List<String> sequences;
}
