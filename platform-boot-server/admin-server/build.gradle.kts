import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springSecurityCommons)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springCloudCommonsStarter)
    implementation(rootProject.libs.bundles.springBootAdminServerStarter)
    // development
    developmentOnly(rootProject.libs.bundles.baseDevelopment)
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("admin-server.jar")
}
