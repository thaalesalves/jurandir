package es.thalesalv.jurandir.adapter.controller.server.aigame;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.domain.model.aigame.ScriptSet;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scripts")
public class ScriptsController {

    @PostMapping("/add")
    public Mono<ScriptSet> createScript(@RequestBody ScriptSet body) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get")
    public Mono<List<ScriptSet>> getAllScripts() {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @GetMapping("/get/{id}")
    public Mono<ScriptSet> getScriptById(@PathVariable UUID id) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/scenario/{id}")
    public Mono<List<ScriptSet>> getScriptByScenarioId(@PathVariable UUID scenarioId) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
