dependencies {
    api(project(":platform-commons:commons-redisson"))
    api("org.redisson:redisson-spring-boot-starter")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-redisson-spring-boot-starter")
}
