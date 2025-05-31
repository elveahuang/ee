import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    // spring extensions
    implementation("de.codecentric:spring-boot-admin-starter-server")
    // development & test
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("admin.jar")
}
