package es.thalesalv.jurandir.domain.model.discord;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpeakActionBody {
    
    @JsonProperty("text")
    private String text;

    @JsonProperty("guild_id")
    private String guildId;

    @JsonProperty("channel_id")
    private String channelId;
}
