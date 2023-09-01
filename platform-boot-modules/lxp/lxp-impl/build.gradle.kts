dependencies {
    //
    api("org.springframework:spring-webmvc")
    api("org.springframework.data:spring-data-jpa")
    // modules
    api(project(":platform-commons:commons-core"))
    api(project(":platform-boot-modules:system:system-api"))
    api(project(":platform-boot-modules:lxp:lxp-api"))
    // redis
    api("org.redisson:redisson")
    api("me.ahoo.cosid:cosid-spring-redis")
    api("me.ahoo.cosid:cosid-spring-boot-starter")
    // sdks
    api("io.minio:minio")
    api("com.github.binarywang:weixin-java-mp")
    api("com.github.binarywang:weixin-java-cp")
    api("com.github.binarywang:weixin-java-miniapp")
    api("com.aliyun:aliyun-java-sdk-core")
    api("com.aliyun:dingtalk")
    api("com.aliyun:imm20170906")
    api("com.aliyun:facebody20191230")
    api("com.aliyun:dysmsapi20170525")
    api("com.aliyun:alimt20181012")
    api("com.aliyun.oss:aliyun-sdk-oss")
    api("com.qcloud:cos_api")
    api("com.tencentcloudapi:tencentcloud-sdk-java-common")
    api("com.tencentcloudapi:tencentcloud-sdk-java-sms")
    api("com.tencentcloudapi:tencentcloud-sdk-java-tmt")
    api("com.larksuite.oapi:oapi-sdk")
    // others
    api("ognl:ognl")
    api("cn.hutool:hutool-captcha")
    api("nl.basjes.parse.useragent:yauaa")
    api("org.hibernate.orm:hibernate-core")
    //
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-lxp-impl")
}
