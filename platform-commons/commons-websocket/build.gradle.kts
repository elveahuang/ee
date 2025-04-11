dependencies {
    api("org.springframework:spring-websocket")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-websocket")
}
