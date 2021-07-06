package es.thalesalv.jurandir.adapter.bean;

import es.thalesalv.jurandir.adapter.model.ControllerResponse;

public interface DiscordActions {

    public ControllerResponse sendMessage(String message, String channelId, String guildId);
}
