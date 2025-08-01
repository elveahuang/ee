dependencies {
    // libs
    implementation(rootProject.libs.bundles.springCommons)
    implementation(rootProject.libs.bundles.springAiCommons)
    implementation(rootProject.libs.bundles.springSecurityCommons)
    implementation(rootProject.libs.bundles.springBootCommons)
    annotationProcessor(rootProject.libs.bundles.springBootAnnotationProcessor)
    implementation(rootProject.libs.bundles.redis)
    implementation(rootProject.libs.bundles.hibernate)
    implementation(rootProject.libs.bundles.rabbit)
    implementation(rootProject.libs.bundles.quartz)
    implementation(rootProject.libs.bundles.wechat)
    implementation(rootProject.libs.bundles.excel)
    implementation(rootProject.libs.bundles.swagger)
    // modules
    api(project(":platform-commons:commons-core"))
    api(project(":platform-boot-modules:system:system-api"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-system-impl")
}
