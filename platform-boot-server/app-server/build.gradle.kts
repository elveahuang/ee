import org.springframework.boot.gradle.tasks.aot.ProcessAot
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.gradle.native)
    alias(libs.plugins.spring.boot)
}

dependencies {
    // libs
    implementation(rootProject.libs.bundles.springAiCommonsStarter)
    implementation(rootProject.libs.bundles.springBootCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityAuthorizationServerStarter)
    implementation(rootProject.libs.bundles.springSecurityResourceServerStarter)
    implementation(rootProject.libs.bundles.springBootAdminClientStarter)
    implementation(rootProject.libs.bundles.rabbitStarter)
    implementation(rootProject.libs.bundles.quartzStarter)
    implementation(rootProject.libs.bundles.swaggerStarter)
    implementation(rootProject.libs.bundles.hibernateStarter)
    implementation(rootProject.libs.bundles.websocketStarter)
    implementation(rootProject.libs.bundles.seleniumStarter)
    implementation(rootProject.libs.bundles.im)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-commons:commons-redis-starter"))
    implementation(project(":platform-boot-modules:system:system-impl"))
    implementation(project(":platform-boot-modules:system:system-security"))
}

graalvmNative {
    toolchainDetection.set(true)
    binaries {
        named("main") {
            buildArgs.add("--allow-incomplete-classpath")
            buildArgs.add("--initialize-at-build-time=org.slf4j")
            buildArgs.add("--initialize-at-build-time=ch.qos.logback")
            buildArgs.add("--initialize-at-build-time=io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess")
            buildArgs.add("--initialize-at-run-time=org.springframework.ai.chat.client.advisor.api.BaseAdvisor")
            buildArgs.add("--initialize-at-run-time=sun.net.dns.ResolverConfigurationImpl")
            buildArgs.add("--trace-class-initialization=org.springframework.util.ClassUtils")
            buildArgs.add("-H:+PrintClassInitialization")
            buildArgs.add("-H:+ReportExceptionStackTraces")
            buildArgs.add("-H:+UnlockExperimentalVMOptions")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:-DeadlockWatchdogExitOnTimeout")
            buildArgs.add("-H:DeadlockWatchdogInterval=0")
            buildArgs.add("-J-Xmx16G")
            verbose.set(true)
            fallback.set(false)
            quickBuild.set(true)
            sharedLibrary.set(false)
            imageName.set("app")
        }
    }
}

tasks.withType<ProcessAot> {
    args("--spring.profiles.active=native")
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveBaseName.set("app.jar")
}
