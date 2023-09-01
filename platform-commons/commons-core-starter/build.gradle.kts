dependencies {
    // spring
    api("org.springframework:spring-webmvc")
    api("org.springframework.boot:spring-boot-starter-validation")
    api("org.springframework.boot:spring-boot-autoconfigure")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.security:spring-security-oauth2-jose")
    compileOnly("org.springframework.security:spring-security-core")
    compileOnly("org.springframework.security:spring-security-web")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
    compileOnly("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")
    compileOnly("org.springframework.boot:spring-boot-starter-thymeleaf")
    // spring extensions
    compileOnly("me.ahoo.cosid:cosid-spring-boot-starter")
    compileOnly("com.baomidou:mybatis-plus-boot-starter")
    // hibernate & redis & cache & mybatis
    compileOnly("org.redisson:redisson")
    compileOnly("org.hibernate.orm:hibernate-core")
    compileOnly("me.ahoo.cosid:cosid-spring-redis")
    compileOnly("com.github.ben-manes.caffeine:caffeine")
    compileOnly("org.mybatis:mybatis")
    compileOnly("org.mybatis:mybatis-spring")
    compileOnly("com.baomidou:mybatis-plus-core")
    compileOnly("com.baomidou:mybatis-plus-extension")
    compileOnly("com.github.jsqlparser:jsqlparser")
    // sdks
    compileOnly("io.minio:minio")
    compileOnly("com.github.binarywang:weixin-java-mp")
    compileOnly("com.github.binarywang:weixin-java-cp")
    compileOnly("com.github.binarywang:weixin-java-miniapp")
    compileOnly("com.aliyun:aliyun-java-sdk-core")
    compileOnly("com.aliyun:dingtalk")
    compileOnly("com.aliyun:imm20170906")
    compileOnly("com.aliyun:facebody20191230")
    compileOnly("com.aliyun:dysmsapi20170525")
    compileOnly("com.aliyun:alimt20181012")
    compileOnly("com.aliyun.oss:aliyun-sdk-oss")
    compileOnly("com.qcloud:cos_api")
    compileOnly("com.tencentcloudapi:tencentcloud-sdk-java-common")
    compileOnly("com.tencentcloudapi:tencentcloud-sdk-java-sms")
    compileOnly("com.tencentcloudapi:tencentcloud-sdk-java-tmt")
    compileOnly("com.larksuite.oapi:oapi-sdk")
    // modules
    api(project(":platform-commons:commons-core"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-core-starter")
}
