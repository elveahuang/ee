import org.springframework.boot.gradle.tasks.aot.ProcessAot
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("org.graalvm.buildtools.native")
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    // spring extensions
    implementation("de.codecentric:spring-boot-admin-starter-client")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    // commons modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-commons:commons-cosid-starter"))
    implementation(project(":platform-commons:commons-rabbit-starter"))
    implementation(project(":platform-commons:commons-redisson-starter"))
    implementation(project(":platform-commons:commons-hibernate-starter"))
    implementation(project(":platform-commons:commons-oapis-starter"))
    implementation(project(":platform-commons:commons-security-starter"))
    implementation(project(":platform-commons:commons-quartz-starter"))
    implementation(project(":platform-commons:commons-websocket-starter"))
    // application modules
    implementation(project(":platform-boot-modules:security"))
    implementation(project(":platform-boot-modules:system:system-impl"))
    implementation(project(":platform-boot-modules:lxp:lxp-impl"))
    // development & test
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("jakarta.servlet:jakarta.servlet-api")
}

graalvmNative {
    toolchainDetection.set(true)
    binaries {
        named("main") {
            buildArgs.add("--initialize-at-build-time=org.slf4j")
            buildArgs.add("--initialize-at-build-time=ch.qos.logback")
            buildArgs.add("--initialize-at-run-time=sun.net.dns.ResolverConfigurationImpl")
            buildArgs.add("--initialize-at-run-time=me.ahoo.cosid.spring.redis.SpringRedisMachineIdDistributor")
            buildArgs.add("--initialize-at-run-time=me.ahoo.cosid.machine.ManualMachineIdDistributor")
            buildArgs.add("--trace-class-initialization=org.springframework.util.ClassUtils")
            buildArgs.add("--trace-class-initialization=io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:+ReportExceptionStackTraces")
            buildArgs.add("-H:+UnlockExperimentalVMOptions")
            buildArgs.add("-H:+PrintClassInitialization")
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
    archiveFileName.set("app.jar")
}
