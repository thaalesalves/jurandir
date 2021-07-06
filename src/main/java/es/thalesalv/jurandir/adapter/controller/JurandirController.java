package es.thalesalv.jurandir.adapter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.adapter.bean.DiscordActions;
import es.thalesalv.jurandir.adapter.bean.GPTAdapter;
import es.thalesalv.jurandir.adapter.model.ControllerResponse;
import es.thalesalv.jurandir.application.service.JurandirConfigService;
import es.thalesalv.jurandir.domain.exception.JurandirConfigException;
import es.thalesalv.jurandir.domain.model.ContextEntry;
import es.thalesalv.jurandir.domain.model.JurandirConfig;
import es.thalesalv.jurandir.domain.model.SpeakActionBody;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class JurandirController {

    private final JurandirConfigService jurandirConfigService;
    private final DiscordActions actions;
    private final GPTAdapter gpt;

    @PostMapping("/action/speak")
    public Mono<ControllerResponse> speak(@RequestBody SpeakActionBody body) {
        try {
            return Mono.just(actions.sendMessage(body.getText(), body.getChannelId(), body.getGuildId()));
        } catch (Exception e) {
            return Mono.just(ControllerResponse.builder().response(e.getMessage()).message("Error during speak action").build());
        }
    }

    @PostMapping("/context/entry/add")
    public Mono<ControllerResponse> addContextEntry(@RequestBody ContextEntry body) {
        try {
            return Mono.just(gpt.addContextEntry(body));
        } catch (Exception e) {
            return Mono.just(ControllerResponse.builder().response(e.getMessage()).message("Error adding context entry").build());
        }
    }

    @PatchMapping("/config/update")
    public Mono<JurandirConfig> updateConfig(@RequestBody JurandirConfig body) {
        try {
            return Mono.just(body).map(config -> {
                try {
                    return jurandirConfigService.updateConfig(config);
                } catch (Exception e) {
                    throw new JurandirConfigException("Error updating config through API", e);
                }
            });
        } catch (Exception e) {
            throw e;
        }
    }
}
