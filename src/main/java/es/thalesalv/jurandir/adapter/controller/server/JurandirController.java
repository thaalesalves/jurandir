package es.thalesalv.jurandir.adapter.controller.server;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.application.service.JurandirConfigService;
import es.thalesalv.jurandir.domain.exception.JurandirConfigException;
import es.thalesalv.jurandir.domain.model.bot.JurandirConfig;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/config")
public class JurandirController {

    private final JurandirConfigService jurandirConfigService;

    @PatchMapping("/update")
    public Mono<JurandirConfig> updateConfig(@RequestBody JurandirConfig body) {
        return Mono.just(body).map(config -> {
            try {
                return jurandirConfigService.updateConfig(config);
            } catch (Exception e) {
                throw new JurandirConfigException("Error updating config through API", e);
            }
        });
    }
}
