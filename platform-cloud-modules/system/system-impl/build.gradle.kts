dependencies {
    // libs
    implementation(rootProject.libs.bundles.springCore)
    implementation(rootProject.libs.bundles.springAiCore)
    implementation(rootProject.libs.bundles.springBootCore)
    implementation(rootProject.libs.bundles.springSecurityCore)
    implementation(rootProject.libs.bundles.redis)
    implementation(rootProject.libs.bundles.mybatis)
    implementation(rootProject.libs.bundles.rabbit)
    implementation(rootProject.libs.bundles.quartz)
    implementation(rootProject.libs.bundles.excel)
    implementation(rootProject.libs.bundles.im)
    // modules
    api(project(":platform-commons:commons-core"))
    api(project(":platform-cloud-modules:system:system-api"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-impl")
}
