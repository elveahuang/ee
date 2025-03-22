dependencies {
    api("org.springframework.amqp:spring-amqp")
    api("org.springframework.amqp:spring-rabbit")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-rabbit")
}
