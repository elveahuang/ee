dependencies {
    // libs
    implementation(rootProject.libs.bundles.excel)
    implementation(rootProject.libs.bundles.springSecurityCommons)
    implementation(rootProject.libs.bundles.springCloudCommons)
    // modules
    api(project(":platform-commons:commons-core"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-lxp-api")
}
