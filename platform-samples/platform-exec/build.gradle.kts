dependencies {
    implementation(project(":platform-commons:commons-javacv"))
}

tasks.register<Copy>("copyLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    into(layout.buildDirectory.dir("libs-external"))
}
