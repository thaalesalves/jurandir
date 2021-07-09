package es.thalesalv.jurandir.adapter.controller.server.aigame;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.domain.model.aigame.ContextEntry;
import es.thalesalv.jurandir.domain.model.aigame.ContextEntrySet;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/context_entries")
public class ContextEntryController {

    @PostMapping("/add")
    public Mono<ContextEntry> createContextEntry(@RequestBody ContextEntry body) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/add/set")
    public Mono<ContextEntrySet> createContextEntrySet(@RequestBody ContextEntrySet body) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get")
    public Mono<List<ContextEntry>> getAllContextEntries() {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/set")
    public Mono<List<ContextEntrySet>> getAllContextEntrySets() {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @GetMapping("/get/{id}")
    public Mono<ContextEntry> getContextEntryById(@PathVariable UUID id) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/set/{id}")
    public Mono<ContextEntrySet> getContextEntrySetById(@PathVariable UUID id) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/scenario/{scenario_id}")
    public Mono<List<ContextEntrySet>> getContextEntrySetByScenarioId(@PathVariable UUID scenarioId) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/story/{story_id}")
    public Mono<List<ContextEntrySet>> getContextEntrySetByStoryId(@PathVariable UUID storyId) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
