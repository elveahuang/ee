dependencies {
    api(project(":platform-commons:commons-ai"))
    api("org.springframework.ai:spring-ai-openai-spring-boot-starter")
    api("org.springframework.ai:spring-ai-zhipuai-spring-boot-starter")
    api("org.springframework.ai:spring-ai-qianfan-spring-boot-starter")
    api("org.springframework.ai:spring-ai-moonshot-spring-boot-starter")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-ai-starter")
}
