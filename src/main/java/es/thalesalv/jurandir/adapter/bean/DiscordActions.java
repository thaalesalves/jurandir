// package es.thalesalv.jurandir.adapter.bean;

// import org.springframework.beans.factory.BeanFactory;
// import org.springframework.stereotype.Component;

// import es.thalesalv.jurandir.adapter.model.ControllerResponse;
// import lombok.RequiredArgsConstructor;
// import net.dv8tion.jda.api.JDA;
// import net.dv8tion.jda.api.entities.TextChannel;

// @Component
// @RequiredArgsConstructor
// public class DiscordActions {

//     private final BeanFactory factory;

//     public ControllerResponse sendMessage(String message, String channelId, String guildId) {
//         try {
//             JDA jda = (JDA) factory.getBean("JDA");
//             TextChannel channel = jda.getGuildById(guildId).getTextChannelById(channelId);
//             return ControllerResponse.builder()
//                 .response(channel.sendMessage(message).complete().getContentDisplay())
//                 .build();
//         } catch (Exception e) {
//             return ControllerResponse.builder()
//                     .response(e.getMessage())
//                     .build();
//         }
//     }
// }
