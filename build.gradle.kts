plugins {
    id("idea")
    id("java")
    id("application")
    id("java-library")
    id("io.spring.dependency-management") version "1.1.3"
    id("com.google.osdetector") version "1.7.3" apply false
    id("org.hibernate.orm") version "6.2.9.Final" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.10" apply false
    id("org.springframework.boot") version "3.1.4" apply false
    id("org.graalvm.buildtools.native") version "0.9.27" apply false
}

allprojects {
    apply(plugin = "idea")
    apply(plugin = "application")
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/spring") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.modulith:spring-modulith-bom:1.0.1")
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.4")
            mavenBom("org.springframework.shell:spring-shell-dependencies:3.1.4")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.4")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2022.0.0.0")
            mavenBom("com.tencent.cloud:spring-cloud-tencent-dependencies:1.12.1-2022.0.4")
            mavenBom("de.codecentric:spring-boot-admin-dependencies:3.1.7")
            mavenBom("io.grpc:grpc-bom:1.58.0")
            mavenBom("cn.hutool:hutool-bom:5.8.22")
            mavenBom("me.ahoo.cosid:cosid-bom:2.5.3")
            mavenBom("org.mockito:mockito-bom:5.5.0")
            mavenBom("org.jetbrains.kotlin:kotlin-bom:1.9.20-RC")
        }

        dependencies {
            // spring authorization server
            dependency("org.springframework.security:spring-security-oauth2-authorization-server:1.1.2")
            // mapstruct & lombok
            dependency("org.mapstruct:mapstruct:1.5.5.Final")
            dependency("org.mapstruct:mapstruct-processor:1.5.5.Final")
            dependency("org.projectlombok:lombok-mapstruct-binding:0.2.0")
            // openapi & swagger & springdoc
            dependency("io.swagger.core.v3:swagger-core-jakarta:2.2.16")
            dependency("io.swagger.core.v3:swagger-annotations:2.2.16")
            dependency("io.swagger.core.v3:swagger-annotations-jakarta:2.2.16")
            dependency("io.swagger.core.v3:swagger-models:2.2.16")
            dependency("io.swagger.core.v3:swagger-models-jakarta:2.2.16")
            dependency("org.springdoc:springdoc-openapi-starter-common:2.2.0")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
            dependency("org.springdoc:springdoc-openapi-starter-webflux-ui:2.2.0")
            // database & nosql
            dependency("org.mybatis:mybatis:3.5.13")
            dependency("org.mybatis:mybatis-spring:3.0.2")
            dependency("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")
            dependency("org.mybatis.generator:mybatis-generator-core:1.4.2")
            dependency("com.baomidou:mybatis-plus:3.5.3.2")
            dependency("com.baomidou:mybatis-plus-annotation:3.5.3.2")
            dependency("com.baomidou:mybatis-plus-core:3.5.3.2")
            dependency("com.baomidou:mybatis-plus-extension:3.5.3.2")
            dependency("com.baomidou:mybatis-plus-generator:3.5.3.2")
            dependency("com.baomidou:mybatis-plus-boot-starter:3.5.3.2")
            dependency("org.apache.shardingsphere:shardingsphere-jdbc-core:5.4.0")
            dependency("com.github.jsqlparser:jsqlparser:4.6")
            dependency("org.redisson:redisson:3.23.5")
            dependency("org.redisson:redisson-spring-data-31:3.23.5")
            dependency("org.redisson:redisson-spring-boot-starter:3.23.5")
            // others
            dependency("com.nimbusds:nimbus-jose-jwt:9.36")
            dependency("com.nimbusds:oauth2-oidc-sdk:11.1")
            dependency("org.json:json:20230618")
            dependency("com.alibaba:fastjson:2.0.41")
            dependency("com.github.spotbugs:spotbugs-annotations:4.7.3")
            dependency("org.jetbrains:annotations:24.0.1")
            dependency("org.ow2.asm:asm:9.6")
            dependency("ognl:ognl:3.4.2")
            dependency("org.lionsoul:ip2region:2.7.0")
            dependency("com.squareup.okio:okio:3.5.0")
            dependency("com.google.guava:guava:32.1.2-jre")
            dependency("joda-time:joda-time:2.12.5")
            dependency("com.github.oshi:oshi-core:6.4.5")
            dependency("com.github.oshi:oshi-core-java11:6.4.5")
            dependency("net.java.dev.jna:jna-platform:5.13.0")
            dependency("nl.basjes.parse.useragent:yauaa:7.22.0")
            dependency("net.logstash.logback:logstash-logback-encoder:7.4")
            dependency("com.google.protobuf:protobuf-java:3.24.3")
            dependency("com.xuxueli:xxl-job-core:2.4.0")
            dependency("org.apache.shardingsphere.elasticjob:elasticjob-lite-core:3.0.3")
            dependency("org.apache.shardingsphere.elasticjob:elasticjob-lite-spring-core:3.0.3")
            dependency("org.apache.httpcomponents:httpcore:4.4.16")
            dependency("org.apache.httpcomponents:httpcore-nio:4.4.16")
            dependency("org.apache.httpcomponents:httpclient:4.5.14")
            dependency("org.apache.httpcomponents:httpmime:4.5.14")
            dependency("net.bytebuddy:byte-buddy:1.14.8")
            dependency("net.bytebuddy:byte-buddy-agent:1.14.8")
            dependency("org.bouncycastle:bcpkix-jdk15on:1.70")
            dependency("org.bouncycastle:bcprov-jdk15on:1.70")
            dependency("org.bouncycastle:bcutil-jdk15on:1.70")
            // xml & docs
            dependency("xalan:xalan:2.7.3")
            dependency("org.jsoup:jsoup:1.16.1")
            dependency("xerces:xercesImpl:2.12.2")
            dependency("com.alibaba:easyexcel:3.3.2")
            dependency("org.apache.poi:poi:5.2.3")
            dependency("org.apache.poi:poi-ooxml:5.2.3")
            dependency("org.apache.poi:poi-ooxml-lite:5.2.3")
            dependency("com.google.zxing:core:3.5.2")
            dependency("com.google.zxing:javase:3.5.2")
            dependency("org.jodconverter:jodconverter-local:4.4.6")
            dependency("org.jodconverter:jodconverter-local-lo:4.4.6")
            dependency("org.jodconverter:jodconverter-remote:4.4.6")
            dependency("org.jodconverter:jodconverter-spring-boot-starter:4.4.6")
            dependency("org.libreoffice:juh:7.6.1")
            dependency("org.libreoffice:jurt:7.6.1")
            dependency("org.libreoffice:ridl:7.6.1")
            dependency("org.libreoffice:unoil:7.6.1")
            dependency("org.dom4j:dom4j:2.1.4")
            dependency("org.apache.pdfbox:pdfbox:3.0.0")
            dependency("net.coobird:thumbnailator:0.4.20")
            dependency("net.imagej:ij:1.54f")
            dependency("com.twelvemonkeys.imageio:imageio-jpeg:3.9.4")
            dependency("com.thoughtworks.xstream:xstream:1.4.20")
            // sdks
            dependency("io.minio:minio:8.5.5")
            dependency("com.github.binarywang:weixin-java-mp:4.5.0")
            dependency("com.github.binarywang:weixin-java-cp:4.5.0")
            dependency("com.github.binarywang:weixin-java-miniapp:4.5.0")
            dependency("com.github.binarywang:weixin-java-pay:4.5.0")
            dependency("com.aliyun:aliyun-java-sdk-core:4.6.3")
            dependency("com.aliyun:tea-openapi:0.3.0")
            dependency("com.aliyun:tea-rpc:0.1.3")
            dependency("com.aliyun:tea-rpc-util:0.1.3")
            dependency("com.aliyun:dingtalk:2.0.41")
            dependency("com.aliyun:imm20170906:1.23.8")
            dependency("com.aliyun:facebody20191230:5.0.0")
            dependency("com.aliyun:dysmsapi20170525:2.0.24")
            dependency("com.aliyun:alimt20181012:1.2.0")
            dependency("com.aliyun.oss:aliyun-sdk-oss:3.17.1")
            dependency("com.alipay.sdk:alipay-sdk-java:4.38.85.ALL")
            dependency("com.qcloud:cos_api:5.6.173")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-common:3.1.853")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-sms:3.1.853")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-tmt:3.1.853")
            dependency("com.larksuite.oapi:oapi-sdk:2.0.27")
            // commons
            dependency("commons-io:commons-io:2.13.0")
            dependency("commons-cli:commons-cli:1.5.0")
            dependency("commons-net:commons-net:3.9.0")
            dependency("commons-codec:commons-codec:1.16.0")
            dependency("commons-beanutils:commons-beanutils:1.9.4")
            dependency("commons-fileupload:commons-fileupload:1.5")
            dependency("org.apache.commons:commons-pool2:2.11.1")
            dependency("org.apache.commons:commons-email:1.5")
            dependency("org.apache.commons:commons-csv:1.10.0")
            dependency("org.apache.commons:commons-text:1.10.0")
            dependency("org.apache.commons:commons-lang3:3.13.0")
            dependency("org.apache.commons:commons-compress:1.23.0")
            dependency("org.apache.commons:commons-collections4:4.4")
        }
    }

    // 强制排除未使用的库
    configurations.configureEach {
        exclude(module = "jaxb-api")
        exclude(module = "android-json")
        exclude(module = "commons-logging")
        exclude(module = "commons-dbcp")
        exclude(module = "commons-dbcp2")
        exclude(module = "j2objc-annotations")
        exclude(module = "junit")
        exclude(module = "junit-vintage-engine")
        exclude(module = "log4j")
        exclude(module = "ehcache")
        exclude(module = "poi-ooxml-schemas")
        exclude(module = "javax.servlet-api")
        exclude(module = "javax.activation-api")
        exclude(module = "validation-api")
    }
}

subprojects {
    dependencies {
        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("org.springframework:spring-webflux")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        // mapstruct & lombok
        implementation("org.mapstruct:mapstruct")
        implementation("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok-mapstruct-binding")
        annotationProcessor("org.mapstruct:mapstruct-processor")
        annotationProcessor("org.projectlombok:lombok")
        annotationProcessor("org.hibernate.orm:hibernate-jpamodelgen")
        testImplementation("org.mapstruct:mapstruct")
        testImplementation("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok-mapstruct-binding")
        testAnnotationProcessor("org.mapstruct:mapstruct-processor")
        testAnnotationProcessor("org.projectlombok:lombok")
        testAnnotationProcessor("org.hibernate.orm:hibernate-jpamodelgen")
    }
}
