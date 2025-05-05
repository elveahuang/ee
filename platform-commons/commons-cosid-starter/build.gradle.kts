dependencies {
    api(project(":platform-commons:commons-cosid"))
    api("me.ahoo.cosid:cosid-spring-boot-starter")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-cosid-starter")
}
