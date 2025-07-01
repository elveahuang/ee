dependencies {
    api(project(":platform-commons:commons-playwright"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-playwright-starter")
}
