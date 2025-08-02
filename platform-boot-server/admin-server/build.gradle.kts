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
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("app.jar")
    manifest {
        attributes("Spring-Boot-Native-Processed" to "false")
    }
}

tasks.named<BootBuildImage>("bootBuildImage") {
    builder = "paketobuildpacks/builder-noble-java-tiny"
    environment = mapOf(
        "BP_JVM_VERSION" to "21",
        "BP_NATIVE_IMAGE" to "false",
        "BP_SPRING_AOT_ENABLED" to "false",
    )
}
