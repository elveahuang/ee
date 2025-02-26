dependencies {
    api(project(":platform-commons:commons-ai"))
    api("org.springframework.ai:spring-ai-openai-spring-boot-starter")
    api("org.springframework.ai:spring-ai-mcp-client-spring-boot-starter")
    api("org.springframework.ai:spring-ai-mcp-server-spring-boot-starter")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-ai-starter")
}
