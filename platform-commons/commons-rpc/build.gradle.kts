dependencies {
    api("com.xuxueli:xxl-tool")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-rpc")
}
