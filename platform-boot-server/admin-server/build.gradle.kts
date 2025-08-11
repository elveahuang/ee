import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(rootProject.libs.bundles.springSecurityCommons)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springBootCommonsStarter)
    implementation(rootProject.libs.bundles.springBootAdminServerStarter)
    implementation(rootProject.libs.bundles.logging)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("app.jar")
    manifest {
        attributes("Spring-Boot-Native-Processed" to "false")
    }
}

tasks.named<BootBuildImage>("bootBuildImage") {
    builder = "bellsoft/buildpacks.builder:musl"
    environment = mapOf(
        "BP_LOG_LEVEL" to "debug",
        "BP_JVM_VERSION" to "21",
        "BPE_DELIM_JAVA_TOOL_OPTIONS" to " ",
        "BPE_APPEND_JAVA_TOOL_OPTIONS" to "-XX:+HeapDumpOnOutOfMemoryError",
    )
    bindings = listOf(
        "$rootDir/tools/buildpacks/bindings-remote:/platform/bindings"
    )
    cleanCache = true
    imageName = "boot-admin-server"
}
