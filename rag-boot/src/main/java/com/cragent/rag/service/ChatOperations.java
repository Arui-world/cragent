package com.cragent.rag.service;

import reactor.core.publisher.Flux;

public interface ChatOperations {

	String chat(String question);

	Flux<String> streamChat(String question);
}
