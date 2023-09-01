dependencies {
    api(project(":platform-commons:commons-core"))
    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-api")
}
