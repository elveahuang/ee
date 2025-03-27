dependencies {
    api(project(":platform-commons:commons-crawler"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-crawler-starter")
}
