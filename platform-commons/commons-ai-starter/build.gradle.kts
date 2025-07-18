dependencies {
    api(project(":platform-commons:commons-ai"))
    api("org.springframework.ai:spring-ai-starter-model-openai")
    api("org.springframework.ai:spring-ai-starter-model-deepseek")
    // MCP
    api("org.springframework.ai:spring-ai-starter-mcp-client")
    api("org.springframework.ai:spring-ai-starter-mcp-server")
    api("org.springframework.ai:spring-ai-starter-mcp-server-webmvc")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-ai-starter")
}
