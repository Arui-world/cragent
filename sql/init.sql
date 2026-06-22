CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS t_intent_node (
    id                    VARCHAR(20) PRIMARY KEY,
    name                  VARCHAR(64) NOT NULL,
    description           VARCHAR(512),
    level                 SMALLINT NOT NULL,
    parent_id             VARCHAR(20),
    kind                  SMALLINT NOT NULL DEFAULT 0,
    collection_name       VARCHAR(128),
    mcp_tool_id           VARCHAR(128),
    examples              TEXT,
    prompt_snippet        TEXT,
    prompt_template       TEXT,
    top_k                 INTEGER,
    sort_order            INTEGER DEFAULT 0,
    enabled               SMALLINT DEFAULT 1,
    create_time           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted               SMALLINT DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_intent_node_parent_id ON t_intent_node(parent_id);
CREATE INDEX IF NOT EXISTS idx_intent_node_enabled_deleted ON t_intent_node(enabled, deleted);

CREATE TABLE IF NOT EXISTS ai_chat_memory (
    id                    VARCHAR(255) PRIMARY KEY,
    conversation_id       VARCHAR(255) NOT NULL,
    role                  VARCHAR(50) NOT NULL,
    content               TEXT NOT NULL,
    create_time           TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_ai_chat_memory_conversation ON ai_chat_memory(conversation_id);
