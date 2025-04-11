dependencies {
    api(project(":platform-commons:commons-rabbit"))
    api("org.springframework.boot:spring-boot-starter-amqp")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-rabbit-starter")
}
