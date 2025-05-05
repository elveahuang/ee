dependencies {
    api("org.springframework.data:spring-data-elasticsearch")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-elastic")
}
