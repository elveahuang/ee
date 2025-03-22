dependencies {
    api("org.springframework.security:spring-security-core")
    api("org.springframework.security:spring-security-web")
    api("org.springframework.security:spring-security-oauth2-jose")
    api("org.springframework.security:spring-security-oauth2-core")
    api("org.springframework.security:spring-security-oauth2-resource-server")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-security")
}
