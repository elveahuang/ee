dependencies {
    api("org.seleniumhq.selenium:selenium-java")
    api("org.seleniumhq.selenium:selenium-api")
    api("org.seleniumhq.selenium:selenium-chrome-driver")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-crawler")
}
