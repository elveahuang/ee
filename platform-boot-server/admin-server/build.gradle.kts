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
}

tasks.named<BootBuildImage>("bootBuildImage") {
    builder = "bellsoft/buildpacks.builder:musl"
    environment.put("JAVA_TOOL_OPTIONS", "-XX:+UseZGC")
    environment.put("BP_SPRING_AOT_ENABLED", "false")
    environment.put("BP_NATIVE_IMAGE", "false")
    environment.put("BP_JVM_CDS_ENABLED", "true")
    environment.put("BP_JVM_VERSION", "21")
}
