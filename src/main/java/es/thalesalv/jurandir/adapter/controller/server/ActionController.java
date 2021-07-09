package es.thalesalv.jurandir.adapter.controller.server;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.adapter.bean.DiscordActions;
import es.thalesalv.jurandir.adapter.model.ControllerResponse;
import es.thalesalv.jurandir.domain.model.discord.SpeakActionBody;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actions")
public class ActionController {

    private final DiscordActions actions;

    @PostMapping("/action/speak")
    public Mono<ControllerResponse> speak(@RequestBody SpeakActionBody body) {
        try {
            return Mono.just(actions.sendMessage(body.getText(), body.getChannelId(), body.getGuildId()));
        } catch (Exception e) {
            return Mono.just(ControllerResponse.builder()
                    .response(e.getMessage())
                    .message("Error during speak action")
                    .build());
        }
    }
}
