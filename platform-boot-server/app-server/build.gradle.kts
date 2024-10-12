import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("org.graalvm.buildtools.native")
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
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter")
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
    implementation("com.aliyun:facebody20191230")
    implementation("com.aliyun:dysmsapi20170525")
    implementation("com.aliyun:alimt20181012")
    implementation("com.aliyun.oss:aliyun-sdk-oss")
    implementation("com.qcloud:cos_api")
    implementation("com.tencentcloudapi:tencentcloud-sdk-java-common")
    implementation("com.tencentcloudapi:tencentcloud-sdk-java-sms")
    implementation("com.tencentcloudapi:tencentcloud-sdk-java-tmt")
    implementation("com.larksuite.oapi:oapi-sdk")
    // webjars
    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars:jquery")
    implementation("org.webjars:popper.js")
    implementation("org.webjars:bootstrap")
    implementation("org.webjars.npm:bootstrap-icons")
    // development & test
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("jakarta.servlet:jakarta.servlet-api")
}

graalvmNative {
    toolchainDetection.set(true)
    binaries {
        named("main") {
            buildArgs.add("--initialize-at-build-time=org.slf4j")
            buildArgs.add("--initialize-at-build-time=ch.qos.logback")
            buildArgs.add("--initialize-at-build-time=org.slf4j.LoggerFactory")
            buildArgs.add("--initialize-at-run-time=sun.net.dns.ResolverConfigurationImpl")
            buildArgs.add("--initialize-at-run-time=me.ahoo.cosid.spring.redis.SpringRedisMachineIdDistributor")
            buildArgs.add("--initialize-at-run-time=me.ahoo.cosid.machine.ManualMachineIdDistributor")
            buildArgs.add("--trace-class-initialization=org.springframework.util.ClassUtils")
            buildArgs.add("-H:+ReportUnsupportedElementsAtRuntime")
            buildArgs.add("-H:+ReportExceptionStackTraces")
            buildArgs.add("-H:+UnlockExperimentalVMOptions")
            buildArgs.add("-H:+PrintClassInitialization")
            verbose.set(true)
            fallback.set(false)
            quickBuild.set(true)
            sharedLibrary.set(false)
            imageName.set("app")
        }
    }
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("app.jar")
}
