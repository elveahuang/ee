dependencies {
    // libs
    compileOnly(rootProject.libs.bundles.springBootCommons)
    compileOnly(rootProject.libs.bundles.springBootCommonsStarter)
    compileOnly(rootProject.libs.bundles.springAiCommons)
    compileOnly(rootProject.libs.bundles.springAiCommonsStarter)
    compileOnly(rootProject.libs.bundles.springSecurityCommons)
    compileOnly(rootProject.libs.bundles.springSecurityCommonsStarter)
    compileOnly(rootProject.libs.bundles.mybatis)
    compileOnly(rootProject.libs.bundles.mybatisStarter)
    compileOnly(rootProject.libs.bundles.hibernate)
    compileOnly(rootProject.libs.bundles.hibernateStarter)
    compileOnly(rootProject.libs.bundles.selenium)
    compileOnly(rootProject.libs.bundles.seleniumStarter)
    compileOnly(rootProject.libs.bundles.elastic)
    compileOnly(rootProject.libs.bundles.elasticStarter)
    compileOnly(rootProject.libs.bundles.rabbit)
    compileOnly(rootProject.libs.bundles.rabbitStarter)
    compileOnly(rootProject.libs.bundles.rabbit)
    compileOnly(rootProject.libs.bundles.rabbitStarter)
    compileOnly(rootProject.libs.bundles.quartz)
    compileOnly(rootProject.libs.bundles.quartzStarter)
    compileOnly(rootProject.libs.bundles.websocket)
    compileOnly(rootProject.libs.bundles.websocketStarter)
    compileOnly(rootProject.libs.bundles.redis)
    compileOnly(rootProject.libs.bundles.redisStarter)
    compileOnly(rootProject.libs.bundles.office)
    compileOnly(rootProject.libs.bundles.officeStarter)
    compileOnly(rootProject.libs.bundles.sharding)
    compileOnly(rootProject.libs.bundles.im)
    compileOnly(rootProject.libs.bundles.telegram)
    // modules
    api(project(":platform-commons:commons-core"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-core-starter")
}
