dependencies {
    api(project(":platform-commons:commons-quartz"))
    api("org.springframework.boot:spring-boot-starter-quartz")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-quartz-starter")
}
