dependencies {
    // libs
    implementation(rootProject.libs.bundles.springCloudCore)
    implementation(rootProject.libs.bundles.springSecurityCore)
    implementation(rootProject.libs.bundles.excel)
    // modules
    api(project(":platform-commons:commons-core"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-api")
}
