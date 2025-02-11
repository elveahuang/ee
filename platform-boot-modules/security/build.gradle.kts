dependencies {
    // spring
    api("org.springframework.security:spring-security-oauth2-resource-server")
    api("org.springframework.security:spring-security-oauth2-authorization-server")
    // commons modules
    api(project(":platform-commons:commons-core"))
    api(project(":platform-commons:commons-security"))
    // application modules
    api(project(":platform-boot-modules:system:system-api"))
    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-security")
}
