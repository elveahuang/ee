dependencies {
    api(project(":platform-commons:commons-rpc"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-rpc-starter")
}
