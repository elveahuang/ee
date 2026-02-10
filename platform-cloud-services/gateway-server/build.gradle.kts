import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springCloudCoreStarter)
    implementation(rootProject.libs.bundles.springBootReactiveStarter)
    implementation(rootProject.libs.bundles.springCloudGatewayServerStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    implementation(rootProject.libs.bundles.springDocReactiveStarter)
    implementation(rootProject.libs.bundles.baseJakartaServlet)
    implementation(rootProject.libs.bundles.redisStarter)
    implementation(rootProject.libs.bundles.logging)
    implementation(rootProject.libs.bundles.storage)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-cloud-modules:system:system-api"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("gateway-server.jar")
}
