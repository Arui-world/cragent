package com.cragent.rag.controller;

import com.cragent.rag.service.ChatOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
@ConditionalOnBean(ChatOperations.class)
public class ChatController {

	private final ChatOperations chatOperations;

	public ChatController(ChatOperations chatOperations) {
		this.chatOperations = chatOperations;
	}

	@GetMapping
	public String chat(@RequestParam String question) {
		return chatOperations.chat(question);
	}

	@GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamChat(@RequestParam String question) {
		return chatOperations.streamChat(question);
	}
}
