package es.thalesalv.jurandir.application.discord.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface GenericFunction {

    public void execute(String command, String[] params);
    public void setUp(MessageReceivedEvent event);
}
