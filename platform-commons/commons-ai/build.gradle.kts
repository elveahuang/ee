dependencies {
    api("com.openai:openai-java")
    api("org.springframework.ai:spring-ai-client-chat")
    api("org.springframework.ai:spring-ai-deepseek")
    api("org.springframework.ai:spring-ai-mcp")
    api("org.springframework.ai:spring-ai-openai")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-ai")
}
