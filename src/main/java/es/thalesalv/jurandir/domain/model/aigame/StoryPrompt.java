package es.thalesalv.jurandir.domain.model.aigame;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.thalesalv.jurandir.domain.model.GPT;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoryPrompt {

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("guild_id")
    private String guildId;

    @JsonProperty("channel_id")
    private String channelId;

    @JsonProperty("generation")
    private GPT gpt;

    @JsonProperty("context_entries")
    private Set<ContextEntry> contextEntries;

    @JsonProperty("script")
    private ScriptSet scriptSet;
}
