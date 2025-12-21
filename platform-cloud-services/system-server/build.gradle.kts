import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springAiCore)
    implementation(rootProject.libs.bundles.springAiCoreStarter)
    implementation(rootProject.libs.bundles.springBootCore)
    implementation(rootProject.libs.bundles.springBootServletStarter)
    implementation(rootProject.libs.bundles.springCloudCore)
    implementation(rootProject.libs.bundles.springCloudCoreStarter)
    implementation(rootProject.libs.bundles.springSecurityCore)
    implementation(rootProject.libs.bundles.springSecurityCoreStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    implementation(rootProject.libs.bundles.redisStarter)
    implementation(rootProject.libs.bundles.springDocServletStarter)
    implementation(rootProject.libs.bundles.mybatisStarter)
    implementation(rootProject.libs.bundles.rabbitStarter)
    implementation(rootProject.libs.bundles.quartzStarter)
    implementation(rootProject.libs.bundles.websocketStarter)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    implementation(rootProject.libs.bundles.logging)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-cloud-modules:system:system-impl"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveBaseName.set("system-server")
}
