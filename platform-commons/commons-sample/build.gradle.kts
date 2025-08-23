import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(rootProject.libs.bundles.springAiCommonsStarter)
    implementation(rootProject.libs.bundles.springAiAlibabaStarter)
    implementation(rootProject.libs.bundles.springBootCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityCommons)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityClientStarter)
    implementation(rootProject.libs.bundles.redis)
    implementation(rootProject.libs.bundles.redisStarter)
    implementation(rootProject.libs.bundles.mybatis)
    implementation(rootProject.libs.bundles.mybatisStarter)
    implementation(rootProject.libs.bundles.hibernate)
    implementation(rootProject.libs.bundles.hibernateStarter)
    implementation(rootProject.libs.bundles.ai)
    implementation(rootProject.libs.bundles.pdf)
    implementation(rootProject.libs.bundles.image)
    implementation(rootProject.libs.bundles.logging)
    implementation(rootProject.libs.bundles.selenium)
    implementation(rootProject.libs.bundles.playwright)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-commons:commons-javacv"))
}

var clearLibs = tasks.register<Delete>("clearLibs") {
    delete(layout.buildDirectory.dir("libs/libs-internal"));
    delete(layout.buildDirectory.dir("libs/libs-external"));
}

var copyInternalLibs = tasks.register<Copy>("copyInternalLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    include("**/platform-*.jar")
    into(layout.buildDirectory.dir("libs/libs-internal"))
}

var copyExternalLibs = tasks.register<Copy>("copyExternalLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    exclude("**/platform-*.jar")
    into(layout.buildDirectory.dir("libs/libs-external"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn(clearLibs)
    dependsOn(copyExternalLibs)
    dependsOn(copyInternalLibs)
    exclude("*.jar")
    manifest {
        attributes["Manifest-Version"] = 1.0
        attributes["Class-Path"] = configurations.runtimeClasspath.get().files.onEach {
            println(it.name)
        }.joinToString(" ") {
            if (it.name.startsWith("platform-")) "./libs-internal/" + it.name else "./libs-external/" + it.name
        }
    }
    archiveFileName.set("sample.jar")
}
