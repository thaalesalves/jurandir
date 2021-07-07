package es.thalesalv.jurandir.adapter.controller.server;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.adapter.bean.GPTAdapter;
import es.thalesalv.jurandir.domain.model.ContextEntry;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stories")
public class StoriesController {
    
    private final GPTAdapter gpt;

    @PostMapping("/entries/add")
    public Mono<ContextEntry> addContextEntryToStory(@RequestBody ContextEntry body) {
        try {
            return Mono.just(gpt.addContextEntry(body));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/entries/get")
    public Mono<List<ContextEntry>> getContextAllEntries() {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @GetMapping("/entries/get/{id}")
    public Mono<ContextEntry> getContextEntry(@PathVariable UUID id) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/entries/get/story/{story_id}")
    public Mono<List<ContextEntry>> getContextAllEntriesInStory(@PathVariable UUID storyId) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
