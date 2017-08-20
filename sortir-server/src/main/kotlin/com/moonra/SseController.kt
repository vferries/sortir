package com.moonra

import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.ReplayProcessor

@RestController
class SseController {
    private val replayProcessor = ReplayProcessor.create<ServerSentEvent<String>>()

    @GetMapping("/sse/receive/{val}")
    fun receive(@PathVariable("val") s: String) {
        replayProcessor.onNext(ServerSentEvent.builder(s).build())
    }

    @GetMapping("/sse/send")
    fun send(): Flux<ServerSentEvent<String>> {
        return replayProcessor.log("playground")
    }
}