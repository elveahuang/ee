dependencies {
    api(project(":platform-commons:commons-elastic"))
    api("org.springframework.boot:spring-boot-starter-data-elasticsearch")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-elastic-starter")
}
