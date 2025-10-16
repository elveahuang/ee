dependencies {
    // libs
    implementation(rootProject.libs.bundles.springBootServletStarter)
    implementation(rootProject.libs.bundles.springSecurityCoreStarter)
    implementation(rootProject.libs.bundles.springSecurityAuthorizationServerStarter)
    implementation(rootProject.libs.bundles.springSecurityResourceServerStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    implementation(rootProject.libs.bundles.springDocServletStarter)
    implementation(rootProject.libs.bundles.websocket)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-boot-modules:system:system-api"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-security")
}
