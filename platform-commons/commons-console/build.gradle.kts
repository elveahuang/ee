import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(rootProject.libs.bundles.springBootCoreStarter)
    implementation(rootProject.libs.bundles.springBootShellStarter)
    implementation(project(":platform-commons:commons-javacv"))
}

tasks.register<Delete>("clearLibs") {
    delete(layout.buildDirectory.dir("libs/libs-internal"));
    delete(layout.buildDirectory.dir("libs/libs-external"));
}

tasks.register<Copy>("copyInternalLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    include("**/platform-*.jar")
    into(layout.buildDirectory.dir("libs/libs-internal"))
}

tasks.register<Copy>("copyExternalLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    exclude("**/platform-*.jar")
    into(layout.buildDirectory.dir("libs/libs-external"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn("clearLibs")
    dependsOn("copyExternalLibs")
    dependsOn("copyInternalLibs")
    exclude("*.jar")
    manifest {
        attributes["Manifest-Version"] = 1.0
        attributes["Class-Path"] = configurations.runtimeClasspath.get().files.onEach {
            println(it.name)
        }.joinToString(" ") {
            if (it.name.startsWith("platform-")) "./libs-internal/" + it.name else "./libs-external/" + it.name
        }
    }
    archiveFileName.set("console.jar")
}
