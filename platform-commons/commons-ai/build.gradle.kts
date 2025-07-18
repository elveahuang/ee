dependencies {
    api("com.openai:openai-java")
    api("org.springframework.ai:spring-ai-client-chat")
    api("org.springframework.ai:spring-ai-deepseek")
    api("org.springframework.ai:spring-ai-openai")
    api("org.springframework.ai:spring-ai-model-chat-memory-repository-jdbc")
    // MCP
    api("org.springframework.ai:spring-ai-mcp")
    // RAG & Vector
    api("org.springframework.ai:spring-ai-rag")
    api("org.springframework.ai:spring-ai-advisors-vector-store")
    api("org.springframework.ai:spring-ai-elasticsearch-store")
    api("org.springframework.ai:spring-ai-redis-store")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-ai")
}
