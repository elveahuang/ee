import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springBootCommonsStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityAuthorizationServerStarter)
    implementation(rootProject.libs.bundles.springSecurityResourceServerStarter)
    implementation(rootProject.libs.bundles.springCloudCommonsStarter)
    implementation(rootProject.libs.bundles.redisStarter)
    implementation(rootProject.libs.bundles.swaggerStarter)
    implementation(rootProject.libs.bundles.mybatisStarter)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    implementation(rootProject.libs.bundles.logging)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-cloud-modules:system:system-api"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("auth-server.jar")
}
