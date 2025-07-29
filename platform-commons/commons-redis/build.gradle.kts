dependencies {
    api(rootProject.libs.bundles.redis)
    // 用于解决MacOS环境下的兼容性问题
    api(variantOf(rootProject.libs.netty.resolver.dns.native.macos) {
        classifier("osx-aarch_64")
    })
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-redis")
}
