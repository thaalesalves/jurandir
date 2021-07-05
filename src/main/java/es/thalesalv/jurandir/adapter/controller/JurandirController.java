// package es.thalesalv.jurandir.adapter.controller;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;

// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import es.thalesalv.jurandir.adapter.bean.DiscordActions;
// import es.thalesalv.jurandir.adapter.model.ControllerResponse;
// import es.thalesalv.jurandir.adapter.model.SpeakActionBody;
// import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Mono;

// @Component
// @RestController
// @RequiredArgsConstructor
// public class JurandirController {

//     private final DiscordActions actions;

//     @PostMapping("/speak")
//     public Mono<ControllerResponse> speak(@RequestBody SpeakActionBody body) throws JsonMappingException, JsonProcessingException {
//         try {
//         return Mono.just(actions.sendMessage(body.getText(), body.getChannelId(), body.getGuildId()));
//         } catch (Exception e) {
//             return Mono.just(ControllerResponse.builder()
//                     .response(e.getMessage())
//                     .build());
//         }
//     }
// }
