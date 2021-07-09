package es.thalesalv.jurandir.adapter.controller.server.aigame;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.thalesalv.jurandir.domain.model.aigame.Story;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stories")
public class StoriesController {

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
}
