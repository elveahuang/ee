import org.springframework.boot.gradle.tasks.aot.ProcessAot
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springAiCoreStarter)
    implementation(rootProject.libs.bundles.springBootServletStarter)
    implementation(rootProject.libs.bundles.springSecurityCoreStarter)
    implementation(rootProject.libs.bundles.springSecurityAuthorizationServerStarter)
    implementation(rootProject.libs.bundles.springSecurityResourceServerStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    implementation(rootProject.libs.bundles.rabbitStarter)
    implementation(rootProject.libs.bundles.redisStarter)
    implementation(rootProject.libs.bundles.quartzStarter)
    implementation(rootProject.libs.bundles.springDocServletStarter)
    implementation(rootProject.libs.bundles.hibernateStarter)
    implementation(rootProject.libs.bundles.websocketStarter)
    implementation(rootProject.libs.bundles.selenium)
    implementation(rootProject.libs.bundles.storage)
    implementation(rootProject.libs.bundles.im)
    implementation(rootProject.libs.bundles.logging)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-boot-modules:system:system-impl"))
    implementation(project(":platform-boot-modules:system:system-security"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("app.jar")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    builder = "bellsoft/buildpacks.builder:musl"
    environment = mapOf(
        "BP_JVM_VERSION" to "25",
        "BPL_JVM_CDS_ENABLED" to "true",
        "BPE_DELIM_JAVA_TOOL_OPTIONS" to " ",
        "BPE_APPEND_JAVA_TOOL_OPTIONS" to "-XX:+HeapDumpOnOutOfMemoryError",
    )
    bindings = listOf(
        "$rootDir/tools/buildpacks/bindings-remote:/platform/bindings"
    )
    cleanCache = true
    imageName = "app"
}

tasks.withType<ProcessAot> {
    args("--spring.profiles.active=native")
}
