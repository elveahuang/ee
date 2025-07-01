dependencies {
    api("com.microsoft.playwright:playwright")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-playwright")
}
