dependencies {
    // libs
    implementation(rootProject.libs.bundles.springCore)
    implementation(rootProject.libs.bundles.springAiCore)
    implementation(rootProject.libs.bundles.springBootCore)
    implementation(rootProject.libs.bundles.springSecurityCore)
    annotationProcessor(rootProject.libs.bundles.springBootAnnotationProcessor)
    implementation(rootProject.libs.bundles.websocket)
    implementation(rootProject.libs.bundles.redis)
    implementation(rootProject.libs.bundles.hibernate)
    implementation(rootProject.libs.bundles.rabbit)
    implementation(rootProject.libs.bundles.quartz)
    implementation(rootProject.libs.bundles.im)
    implementation(rootProject.libs.bundles.ip)
    implementation(rootProject.libs.bundles.excel)
    implementation(rootProject.libs.bundles.swagger)
    // modules
    api(project(":platform-commons:commons-core"))
    api(project(":platform-boot-modules:system:system-api"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-impl")
}
