import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springCloudCommonsStarter)
    implementation(rootProject.libs.bundles.springCloudGatewayServerStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    implementation(rootProject.libs.bundles.logging)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("gateway-server.jar")
}
