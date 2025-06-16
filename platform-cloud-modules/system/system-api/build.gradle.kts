dependencies {
    // spring
    api("org.springframework.cloud:spring-cloud-openfeign-core")
    // modules
    api(project(":platform-commons:commons-core"))
    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-api")
}
