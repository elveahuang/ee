import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("org.hibernate.orm")
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    // spring extensions
    implementation("de.codecentric:spring-boot-admin-starter-client")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-boot-modules:system:system-impl"))
    implementation(project(":platform-boot-modules:lxp:lxp-impl"))
    implementation(project(":platform-boot-modules:security"))
    // redis
    implementation("org.redisson:redisson")
    implementation("me.ahoo.cosid:cosid-spring-redis")
    implementation("me.ahoo.cosid:cosid-spring-boot-starter")
    // sdks
    implementation("io.minio:minio")
    implementation("com.github.binarywang:weixin-java-mp")
    implementation("com.github.binarywang:weixin-java-cp")
    implementation("com.github.binarywang:weixin-java-miniapp")
    implementation("com.aliyun:aliyun-java-sdk-core")
    implementation("com.aliyun:dingtalk")
    implementation("com.aliyun:imm20170906")
    implementation("com.aliyun:facebody20191230")
    implementation("com.aliyun:dysmsapi20170525")
    implementation("com.aliyun:alimt20181012")
    implementation("com.aliyun.oss:aliyun-sdk-oss")
    implementation("com.qcloud:cos_api")
    implementation("com.tencentcloudapi:tencentcloud-sdk-java-common")
    implementation("com.tencentcloudapi:tencentcloud-sdk-java-sms")
    implementation("com.tencentcloudapi:tencentcloud-sdk-java-tmt")
    implementation("com.larksuite.oapi:oapi-sdk")
    // others
    implementation("ognl:ognl")
    implementation("nl.basjes.parse.useragent:yauaa")
    implementation("cn.hutool:hutool-captcha")
    // development & test
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("jakarta.servlet:jakarta.servlet-api")
}

var clearLibs = tasks.register<Delete>("clearLibs") {
    delete(layout.buildDirectory.dir("libs/libs-internal"));
    delete(layout.buildDirectory.dir("libs/libs-external"));
}

var copyLibs = tasks.register<Copy>("copyLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    include("**/platform-*.jar")
    into(layout.buildDirectory.dir("libs/libs-internal"))
}

var copyExtLibs = tasks.register<Copy>("copyExtLibs") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath)
    exclude("**/platform-*.jar")
    into(layout.buildDirectory.dir("libs/libs-external"))
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn(clearLibs)
    dependsOn(copyExtLibs)
    dependsOn(copyLibs)
    exclude("*.jar")
    manifest {
        attributes["Manifest-Version"] = 1.0
        attributes["Class-Path"] = configurations.runtimeClasspath.get().files.onEach {
            println(it.name)
        }.joinToString(" ") {
            if (it.name.startsWith("platform-")) "./libs-internal/" + it.name else "./libs-external/" + it.name
        }
    }
    archiveFileName.set("app.jar")
}
