dependencies {
    api("org.quartz-scheduler:quartz")
    api("org.quartz-scheduler:quartz-jobs")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-quartz")
}
