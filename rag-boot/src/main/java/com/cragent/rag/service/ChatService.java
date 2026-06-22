package com.cragent.rag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
@ConditionalOnBean(ChatClient.class)
public class ChatService implements ChatOperations {

	private final ChatClient chatClient;

	public ChatService(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@Override
	public String chat(String question) {
		return chatClient.prompt()
				.user(question)
				.call()
				.content();
	}

	@Override
	public Flux<String> streamChat(String question) {
		return chatClient.prompt()
				.user(question)
				.stream()
				.content();
	}
}
