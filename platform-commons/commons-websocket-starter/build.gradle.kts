dependencies {
    api(project(":platform-commons:commons-websocket"))
    api("org.springframework.boot:spring-boot-starter-websocket")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-websocket-starter")
}
