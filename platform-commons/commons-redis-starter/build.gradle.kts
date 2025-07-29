dependencies {
    // libs
    api(rootProject.libs.bundles.redisStarter)
    // modules
    api(project(":platform-commons:commons-redis"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-redis-starter")
}
