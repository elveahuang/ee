plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    implementation(rootProject.libs.bundles.springSecurityCommons)
    implementation(rootProject.libs.bundles.springSecurityCommonsStarter)
    implementation(rootProject.libs.bundles.springSecurityClientStarter)
    implementation(rootProject.libs.bundles.springBootCommonsStarter)
    implementation(rootProject.libs.bundles.mybatis)
    implementation(rootProject.libs.bundles.mybatisStarter)
    implementation(rootProject.libs.bundles.hibernate)
    implementation(rootProject.libs.bundles.hibernateStarter)
    implementation(rootProject.libs.bundles.redis)
    implementation(rootProject.libs.bundles.redisStarter)
    implementation(rootProject.libs.bundles.logging)
    implementation(rootProject.libs.bundles.pdf)
    implementation(rootProject.libs.bundles.ocr)
    implementation(rootProject.libs.bundles.image)
    implementation(rootProject.libs.bundles.ffmpeg)
    implementation(rootProject.libs.bundles.selenium)
    implementation(rootProject.libs.bundles.playwright)
    developmentOnly(rootProject.libs.bundles.springBootDevtools)
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
}
