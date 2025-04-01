dependencies {
    api("org.springframework.data:spring-data-mongodb")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-mongo")
}
