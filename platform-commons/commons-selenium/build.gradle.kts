dependencies {
    api("org.seleniumhq.selenium:selenium-java")
    api("org.seleniumhq.selenium:selenium-api")
    api("io.github.bonigarcia:webdrivermanager")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-selenium")
}
