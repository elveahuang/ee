plugins {
    id("org.springframework.boot")
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    // development & test
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}
