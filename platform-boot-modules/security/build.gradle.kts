dependencies {
    // spring
    api("org.springframework.security:spring-security-cas")
    api("org.springframework.security:spring-security-web")
    api("org.springframework.security:spring-security-oauth2-authorization-server")
    // modules
    api(project(":platform-commons:commons-core"))
    api(project(":platform-boot-modules:system:system-api"))
    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-security")
}
