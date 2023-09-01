import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
}

dependencies {
    // spring
    implementation("org.springframework.security:spring-security-core")
    implementation("org.springframework.security:spring-security-web")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.security:spring-security-oauth2-core")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
    implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    // spring extensions
    implementation("de.codecentric:spring-boot-admin-starter-client")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter")
    implementation("com.baomidou:mybatis-plus-boot-starter")
    // redis
    implementation("org.redisson:redisson")
    implementation("me.ahoo.cosid:cosid-spring-redis")
    implementation("me.ahoo.cosid:cosid-spring-boot-starter")
    // storage
    implementation("io.minio:minio")
    implementation("com.qcloud:cos_api")
    implementation("com.aliyun.oss:aliyun-sdk-oss")
    // oapis
    implementation("com.larksuite.oapi:oapi-sdk")
    implementation("com.github.binarywang:weixin-java-mp")
    implementation("com.github.binarywang:weixin-java-cp")
    implementation("com.github.binarywang:weixin-java-miniapp")
    implementation("com.qcloud:cos_api")
    implementation("com.aliyun:tea-openapi")
    implementation("com.aliyun:dingtalk")
    implementation("com.aliyun:alimt20181012")
    implementation("com.aliyun:dysmsapi20170525")
    // others
    implementation("com.alibaba:easyexcel")
    implementation("org.apache.poi:poi")
    implementation("org.apache.poi:poi-ooxml")
    implementation("org.apache.poi:poi-ooxml-lite")
    // modules
    implementation(project(":platform-commons:commons-core-starter"))
    implementation(project(":platform-cloud-modules:system:system-web"))
    // development & test
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
