dependencies {
    api(project(":platform-commons:commons-docs"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-docs-starter")
}
