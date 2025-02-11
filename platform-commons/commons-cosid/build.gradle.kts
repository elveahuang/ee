dependencies {
    api("me.ahoo.cosid:cosid-spring-redis")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-cosid")
}
