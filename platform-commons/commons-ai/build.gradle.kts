dependencies {
    api("com.openai:openai-java")
    api("com.alibaba:dashscope-sdk-java")
    api("com.tencentcloudapi:tencentcloud-sdk-java-hunyuan")
    api("org.springframework.ai:spring-ai-mcp")
    api("org.springframework.ai:spring-ai-openai")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-ai")
}
