dependencies {
    api(project(":platform-commons:commons-mongo"))
    api("org.springframework.boot:spring-boot-starter-data-mongodb")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-mongo-starter")
}
