package com.cragent.rag;

import com.cragent.rag.service.ChatOperations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=" +
				"org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
				"org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration," +
				"org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration," +
				"org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration," +
				"com.alibaba.cloud.ai.autoconfigure.dashscope.DashScopeChatAutoConfiguration," +
				"com.alibaba.cloud.ai.autoconfigure.dashscope.DashScopeEmbeddingAutoConfiguration," +
				"org.springframework.ai.vectorstore.pgvector.autoconfigure.PgVectorStoreAutoConfiguration",
		"spring.ai.dashscope.enabled=false"
})
class RagApplicationTests {

	@Test
	void contextLoads() {
	}

	@TestConfiguration
	static class TestChatConfiguration {

		@Bean
		ChatOperations chatOperations() {
			return new ChatOperations() {
				@Override
				public String chat(String question) {
					return "test";
				}

				@Override
				public Flux<String> streamChat(String question) {
					return Flux.just("test");
				}
			};
		}
	}
}
