dependencies {
    api(project(":platform-commons:commons-oapis"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-oapis-starter")
}
