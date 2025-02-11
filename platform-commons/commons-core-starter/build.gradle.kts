dependencies {
    // spring
    api("org.springframework.boot:spring-boot-autoconfigure")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-starter-aop")
    compileOnly("org.springframework.boot:spring-boot-starter-thymeleaf")
    compileOnly("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    // modules
    api(project(":platform-commons:commons-core"))
    compileOnly(project(":platform-commons:commons-ai-starter"))
    compileOnly(project(":platform-commons:commons-elastic-starter"))
    compileOnly(project(":platform-commons:commons-mybatis-starter"))
    compileOnly(project(":platform-commons:commons-rabbit-starter"))
    compileOnly(project(":platform-commons:commons-mongo-starter"))
    compileOnly(project(":platform-commons:commons-redisson-starter"))
    compileOnly(project(":platform-commons:commons-hibernate-starter"))
    compileOnly(project(":platform-commons:commons-cosid-starter"))
    compileOnly(project(":platform-commons:commons-oapis-starter"))
    compileOnly(project(":platform-commons:commons-websocket-starter"))
    compileOnly(project(":platform-commons:commons-quartz-starter"))
    compileOnly(project(":platform-commons:commons-security-starter"))
    // database
    compileOnly("org.apache.shardingsphere:shardingsphere-jdbc")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-core-starter")
}
