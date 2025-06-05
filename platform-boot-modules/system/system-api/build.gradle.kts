dependencies {
    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api")
    // modules
    api(project(":platform-commons:commons-core"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-api")
}
