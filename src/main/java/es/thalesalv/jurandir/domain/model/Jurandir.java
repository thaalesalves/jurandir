package es.thalesalv.jurandir.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jurandir {

    @JsonProperty("channel_lock")
    private boolean channelLock;

    @JsonProperty("story_channel")
    private String storyChannel;

    @JsonProperty("locked_channel_id")
    private String lockedChannelId;

    @JsonProperty("authorized_channels")
    private List<Long> authorizedChannels;

    @JsonProperty("admin_users")
    private List<Long> adminUsers;

    @JsonProperty("manager_users")
    private List<Long> managerUsers;
}
