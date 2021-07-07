package es.thalesalv.jurandir.application.bean;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

@Configuration
public class Jurandir {
    
    public void die(JDA jda) {
        jda.shutdown();
        System.exit(0);
    }

    public EmbedBuilder buildBuilder(EmbedBuilder builder) throws Exception {
        try {
            builder.setColor(Color.YELLOW);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd @ HH:mm");
        return now.format(fmt);
    }

    public void joinChannel(AudioManager manager, VoiceChannel channel) {
        manager.openAudioConnection(channel);
    }

    public void leaveChannel(AudioManager manager) {
        manager.closeAudioConnection();
    }

    public Boolean isConnected(AudioManager manager) {
        return manager.isConnected();
    }

    public Boolean isUserInVoiceChannel(GuildVoiceState state) {
        return state.inVoiceChannel();
    }

    public Boolean canConnect(VoiceChannel channel, Member bot) {
        return bot.hasPermission(channel, Permission.VOICE_CONNECT);
    }

    public Boolean canSpeak(VoiceChannel channel, Member bot) {
        return bot.hasPermission(channel, Permission.VOICE_SPEAK);
    }

    public Boolean isAdmin(Member member) {
        return member.hasPermission(Permission.ADMINISTRATOR);
    }
}
