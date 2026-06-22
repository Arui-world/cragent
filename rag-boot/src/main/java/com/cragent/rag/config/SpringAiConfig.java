package com.cragent.rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAiConfig {

	@Bean
	@ConditionalOnBean(ChatClient.Builder.class)
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder
				.defaultSystem("你是一个智能助手")
				.build();
	}
}
