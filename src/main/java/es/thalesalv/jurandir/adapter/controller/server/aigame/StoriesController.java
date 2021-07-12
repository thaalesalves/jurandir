package es.thalesalv.jurandir.adapter.controller.server.aigame;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.adapter.controller.client.GPTStoryInferenceService;
import es.thalesalv.jurandir.adapter.model.KoboldAiResponseBody;
import es.thalesalv.jurandir.domain.model.aigame.Story;
import es.thalesalv.jurandir.domain.model.aigame.StoryPrompt;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stories")
public class StoriesController {

    private final GPTStoryInferenceService inferenceService;

    @PostMapping("/add")
    public Mono<Story> createStory(@RequestBody Story body) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get")
    public Mono<List<Story>> getAllStories() {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @GetMapping("/get/{id}")
    public Mono<Story> getStoryById(@PathVariable UUID id) {
        try {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/generate")
    public Mono<KoboldAiResponseBody> generate(@RequestBody StoryPrompt body) {
        try {
            return inferenceService.prompt(body);
        } catch (Exception e) {
            throw e;
        }
    }
}
