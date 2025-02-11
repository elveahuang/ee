dependencies {
    api("org.springframework.ai:spring-ai-openai")
    api("org.springframework.ai:spring-ai-zhipuai")
    api("org.springframework.ai:spring-ai-qianfan")
    api("org.springframework.ai:spring-ai-moonshot")
    api("org.springframework.experimental:mcp")
    api("org.springframework.experimental:spring-ai-mcp")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-ai")
}
