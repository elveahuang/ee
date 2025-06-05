dependencies {
    api(project(":platform-commons:commons-selenium"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-selenium-starter")
}
