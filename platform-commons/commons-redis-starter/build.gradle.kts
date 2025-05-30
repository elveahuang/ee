dependencies {
    api(project(":platform-commons:commons-redis"))
    api("me.ahoo.cosid:cosid-spring-boot-starter")
    api("org.redisson:redisson-spring-boot-starter")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-redis-spring-boot-starter")
}
