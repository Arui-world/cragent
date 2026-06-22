package com.cragent.rag.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cragent.rag.service.ChatOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import reactor.core.publisher.Flux;

class ChatControllerTest {

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		ChatOperations chatOperations = new ChatOperations() {
			@Override
			public String chat(String question) {
				return "hello from cragent";
			}

			@Override
			public Flux<String> streamChat(String question) {
				return Flux.just("cragent", " assistant");
			}
		};
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ChatController(chatOperations)).build();
	}

	@Test
	void chatReturnsAnswer() throws Exception {
		mockMvc.perform(get("/api/chat").param("question", "hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("hello from cragent"));
	}

	@Test
	void streamChatReturnsServerSentEvents() throws Exception {
		mockMvc.perform(get("/api/chat/stream").param("question", "intro"))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpHeaders.CONTENT_TYPE, containsString("text/event-stream")))
				.andExpect(content().string(containsString("cragent")))
				.andExpect(content().string(containsString("assistant")));
	}
}
