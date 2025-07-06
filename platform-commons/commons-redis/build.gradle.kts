dependencies {
    api("org.redisson:redisson")
    api("org.redisson:redisson-spring-data-35")
    api("io.netty:netty-resolver-dns")
    api("io.netty:netty-resolver-dns-native-macos")
    api("io.netty:netty-resolver-dns-classes-macos")
    api("me.ahoo.cosid:cosid-core")
    api("me.ahoo.cosid:cosid-spring-redis")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-redis")
}
