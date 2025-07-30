import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springSecurityCommons)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springBootAdminServerStarter) {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-webflux")
    }
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveBaseName.set("admin.jar")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    builder = "bellsoft/buildpacks.builder:musl"
}
