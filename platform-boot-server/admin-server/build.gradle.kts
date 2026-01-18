import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(rootProject.libs.bundles.springBootServletStarter)
    implementation(rootProject.libs.bundles.springBootAdminServerStarter)
    implementation(rootProject.libs.bundles.springSecurityCoreStarter)
    implementation(rootProject.libs.bundles.logging)
    implementation(rootProject.libs.bundles.storage)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    // modules
    implementation(project(":platform-commons:commons-core"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes("Spring-Boot-Native-Processed" to "false")
    }
    archiveFileName.set("admin.jar")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    builder = "bellsoft/buildpacks.builder:musl"
    environment = mapOf(
        "BP_LOG_LEVEL" to "debug",
        "BP_JVM_VERSION" to "25",
        "BPE_DELIM_JAVA_TOOL_OPTIONS" to " ",
        "BPE_APPEND_JAVA_TOOL_OPTIONS" to "-XX:+HeapDumpOnOutOfMemoryError",
    )
    bindings = listOf(
        "$rootDir/tools/buildpacks/bindings-remote:/platform/bindings"
    )
    cleanCache = true
    imageName = "boot-admin-server"
}
