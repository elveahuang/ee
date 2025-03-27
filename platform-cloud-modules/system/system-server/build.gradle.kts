import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
    implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    // spring extensions
    implementation("de.codecentric:spring-boot-admin-starter-client")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    // commons modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-commons:commons-cosid-starter"))
    implementation(project(":platform-commons:commons-rabbit-starter"))
    implementation(project(":platform-commons:commons-redisson-starter"))
    implementation(project(":platform-commons:commons-mybatis-starter"))
    implementation(project(":platform-commons:commons-oapis-starter"))
    implementation(project(":platform-commons:commons-security-starter"))
    implementation(project(":platform-commons:commons-quartz-starter"))
    implementation(project(":platform-commons:commons-websocket-starter"))
    // application modules
    implementation(project(":platform-cloud-modules:system:system-impl"))
    // development & test
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("app.jar")
}
