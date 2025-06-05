dependencies {
    api(project(":platform-commons:commons-hibernate"))
    api("org.springframework.boot:spring-boot-starter-jdbc")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-hibernate-starter")
}
