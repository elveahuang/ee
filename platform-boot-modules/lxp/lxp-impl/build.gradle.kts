dependencies {
    // spring
    api("org.springframework:spring-webmvc")
    api("org.springframework.boot:spring-boot")
    // commons modules
    api(project(":platform-commons:commons-ai"))
    api(project(":platform-commons:commons-core"))
    api(project(":platform-commons:commons-hibernate"))
    api(project(":platform-commons:commons-quartz"))
    api(project(":platform-commons:commons-redisson"))
    api(project(":platform-commons:commons-rabbit"))
    api(project(":platform-commons:commons-oapis"))
    api(project(":platform-commons:commons-security"))
    // application modules
    api(project(":platform-boot-modules:lxp:lxp-api"))
    api(project(":platform-boot-modules:system:system-api"))
    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-lxp-impl")
}
