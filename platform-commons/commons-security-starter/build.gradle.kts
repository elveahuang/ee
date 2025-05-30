dependencies {
    api(project(":platform-commons:commons-security"))
    api("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    api("org.springframework.boot:spring-boot-starter-oauth2-client")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-security-starter")
}
