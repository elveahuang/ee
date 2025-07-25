dependencies {
    // libs
    implementation(rootProject.libs.bundles.springBootCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityServerStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    implementation(rootProject.libs.bundles.swaggerStarter)
    implementation(rootProject.libs.bundles.redisStarter)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-boot-modules:system:system-api"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-security")
}
