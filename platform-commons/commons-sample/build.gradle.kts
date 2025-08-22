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
    implementation(rootProject.libs.bundles.logging)
    implementation(rootProject.libs.bundles.image)
    implementation(rootProject.libs.bundles.pdf)
    implementation(rootProject.libs.bundles.ai)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-commons:commons-javacv"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("sample.jar")
}
