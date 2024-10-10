import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("idea")
    id("java")
    id("application")
    id("java-library")
    id("io.spring.dependency-management") version "1.1.6"
    id("com.github.spotbugs") version "6.0.23" apply false
    id("org.springframework.boot") version "3.3.4" apply false
    id("org.jetbrains.kotlin.jvm") version "2.0.20" apply false
    id("org.graalvm.buildtools.native") version "0.10.3" apply false
}

allprojects {
    apply(plugin = "application")
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/spring") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
        maven { url = uri("https://repo.spring.io/milestone") }
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    configure<KotlinJvmProjectExtension> {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            apiVersion.set(KotlinVersion.KOTLIN_2_0)
            jvmTarget.set(JvmTarget.JVM_21)
            languageVersion.set(KotlinVersion.KOTLIN_2_0)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.3.4")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2023.0.1.2")
            mavenBom("com.tencent.cloud:spring-cloud-tencent-dependencies:1.13.2-2023.0.0")
            mavenBom("de.codecentric:spring-boot-admin-dependencies:3.3.4")
            mavenBom("io.grpc:grpc-bom:1.68.0")
            mavenBom("cn.hutool:hutool-bom:5.8.32")
            mavenBom("me.ahoo.cosid:cosid-bom:2.9.8")
            mavenBom("org.mockito:mockito-bom:5.14.1")
            mavenBom("io.netty:netty-bom:4.1.114.Final")
            mavenBom("org.apache.groovy:groovy-bom:4.0.23")
            mavenBom("com.baomidou:mybatis-plus-bom:3.5.8")
            mavenBom("org.jetbrains.kotlin:kotlin-bom:2.0.20")
            mavenBom("org.springframework.ai:spring-ai-bom:1.0.0-M3")
            mavenBom("org.springframework.modulith:spring-modulith-bom:1.2.4")
            mavenBom("org.springframework.shell:spring-shell-dependencies:3.3.3")
        }
        dependencies {
            // spring authorization server
            dependency("org.springframework.security:spring-security-oauth2-authorization-server:1.3.2")
            // mapstruct & lombok
            dependency("org.mapstruct:mapstruct:1.6.2")
            dependency("org.mapstruct:mapstruct-processor:1.6.2")
            dependency("org.projectlombok:lombok-mapstruct-binding:0.2.0")
            // openapi & swagger & springdoc
            dependency("io.swagger.core.v3:swagger-core-jakarta:2.2.22")
            dependency("io.swagger.core.v3:swagger-annotations:2.2.22")
            dependency("io.swagger.core.v3:swagger-annotations-jakarta:2.2.22")
            dependency("io.swagger.core.v3:swagger-models:2.2.22")
            dependency("io.swagger.core.v3:swagger-models-jakarta:2.2.22")
            dependency("org.springdoc:springdoc-openapi-starter-common:2.6.0")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
            dependency("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")
            // database & nosql
            dependency("org.mybatis:mybatis:3.5.16")
            dependency("org.mybatis:mybatis-spring:3.0.4")
            dependency("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
            dependency("org.mybatis.generator:mybatis-generator-core:1.4.2")
            dependency("com.github.jsqlparser:jsqlparser:5.0")
            dependency("org.redisson:redisson:3.37.0")
            dependency("org.redisson:redisson-spring-data-33:3.37.0")
            dependency("org.redisson:redisson-spring-boot-starter:3.37.0")
            dependency("org.apache.shardingsphere:shardingsphere-jdbc:5.5.0")
            // others
            dependency("com.nimbusds:nimbus-jose-jwt:9.41.1")
            dependency("com.nimbusds:oauth2-oidc-sdk:11.19.1")
            dependency("org.json:json:20240303")
            dependency("com.alibaba:fastjson:2.0.53")
            dependency("com.github.spotbugs:spotbugs-annotations:4.8.6")
            dependency("org.jetbrains:annotations:24.1.0")
            dependency("org.ow2.asm:asm:9.7")
            dependency("ognl:ognl:3.4.3")
            dependency("com.maxmind.geoip2:geoip2:4.2.0")
            dependency("org.lionsoul:ip2region:2.7.0")
            dependency("com.squareup.okio:okio:3.9.1")
            dependency("com.google.guava:guava:33.3.1-jre")
            dependency("joda-time:joda-time:2.13.0")
            dependency("com.github.oshi:oshi-core-java11:6.6.4")
            dependency("net.java.dev.jna:jna-platform:5.15.0")
            dependency("net.logstash.logback:logstash-logback-encoder:8.0")
            dependency("com.google.protobuf:protobuf-java:4.28.1")
            dependency("com.xuxueli:xxl-job-core:2.4.1")
            dependency("net.bytebuddy:byte-buddy:1.15.1")
            dependency("net.bytebuddy:byte-buddy-agent:1.15.1")
            dependency("org.bouncycastle:bcpkix-jdk18on:1.78.1")
            dependency("org.bouncycastle:bcprov-jdk18on:1.78.1")
            dependency("org.bouncycastle:bcutil-jdk18on:1.78.1")
            dependency("org.jodd:jodd-bean:5.1.6")
            dependency("org.jodd:jodd-core:5.3.0")
            dependency("org.jodd:jodd-http:6.3.0")
            dependency("org.jodd:jodd-util:6.3.0")
            // xml & pdf & office & image
            dependency("xalan:xalan:2.7.3")
            dependency("xerces:xercesImpl:2.12.2")
            dependency("com.alibaba:easyexcel:4.0.3")
            dependency("org.apache.poi:poi:5.3.0")
            dependency("org.apache.poi:poi-ooxml:5.3.0")
            dependency("org.apache.poi:poi-ooxml-lite:5.3.0")
            dependency("com.google.zxing:core:3.5.3")
            dependency("com.google.zxing:javase:3.5.3")
            dependency("org.jodconverter:jodconverter-local:4.4.8")
            dependency("org.jodconverter:jodconverter-local-lo:4.4.8")
            dependency("org.jodconverter:jodconverter-remote:4.4.8")
            dependency("org.jodconverter:jodconverter-spring-boot-starter:4.4.8")
            dependency("org.libreoffice:juh:24.2.3")
            dependency("org.libreoffice:jurt:24.2.3")
            dependency("org.libreoffice:ridl:24.2.3")
            dependency("org.libreoffice:unoil:24.2.3")
            dependency("net.coobird:thumbnailator:0.4.20")
            dependency("net.imagej:ij:1.54k")
            dependency("com.twelvemonkeys.imageio:imageio-jpeg:3.11.0")
            dependency("org.dom4j:dom4j:2.1.4")
            dependency("com.thoughtworks.xstream:xstream:1.4.20")
            dependency("org.apache.pdfbox:pdfbox:3.0.3")
            dependency("org.dromara.x-easypdf:x-easypdf:3.1.1")
            dependency("org.dromara.x-easypdf:x-easypdf-pdfbox:3.1.1")
            dependency("org.jsoup:jsoup:1.18.1")
            dependency("org.htmlunit:htmlunit:4.4.0")
            // sdks
            dependency("io.minio:minio:8.5.12")
            dependency("com.github.binarywang:weixin-java-channel:4.6.0")
            dependency("com.github.binarywang:weixin-java-cp:4.6.0")
            dependency("com.github.binarywang:weixin-java-miniapp:4.6.0")
            dependency("com.github.binarywang:weixin-java-mp:4.6.0")
            dependency("com.github.binarywang:weixin-java-open:4.6.0")
            dependency("com.github.binarywang:weixin-java-pay:4.6.0")
            dependency("com.alibaba:dashscope-sdk-java:2.16.4")
            dependency("com.aliyun:aliyun-java-sdk-core:4.7.1")
            dependency("com.aliyun:tea-openapi:0.3.5")
            dependency("com.aliyun:tea-rpc:0.1.3")
            dependency("com.aliyun:tea-rpc-util:0.1.3")
            dependency("com.aliyun:dingtalk:2.1.50")
            dependency("com.aliyun:imm20170906:1.23.8")
            dependency("com.aliyun:facebody20191230:5.1.2")
            dependency("com.aliyun:dysmsapi20170525:3.0.0")
            dependency("com.aliyun:alimt20181012:1.3.1")
            dependency("com.aliyun.oss:aliyun-sdk-oss:3.18.1")
            dependency("com.alipay.sdk:alipay-sdk-java:4.39.208.ALL")
            dependency("com.qcloud:cos_api:5.6.231")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-common:3.1.1118")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-kms:3.1.977")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-sms:3.1.1111")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-tmt:3.1.1104")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-iai:3.1.978")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-hunyuan:3.1.1110")
            dependency("com.larksuite.oapi:oapi-sdk:2.3.5")
            // commons
            dependency("commons-io:commons-io:2.17.0")
            dependency("commons-cli:commons-cli:1.9.0")
            dependency("commons-net:commons-net:3.11.1")
            dependency("commons-codec:commons-codec:1.17.1")
            dependency("commons-beanutils:commons-beanutils:1.9.4")
            dependency("org.apache.commons:commons-pool2:2.12.0")
            dependency("org.apache.commons:commons-email:1.6.0")
            dependency("org.apache.commons:commons-csv:1.12.0")
            dependency("org.apache.commons:commons-text:1.12.0")
            dependency("org.apache.commons:commons-lang3:3.17.0")
            dependency("org.apache.commons:commons-compress:1.27.1")
            dependency("org.apache.commons:commons-collections4:4.4")
            // webjars
            dependency("org.webjars:jquery:3.7.1")
            dependency("org.webjars:popper.js:2.11.7")
            dependency("org.webjars:bootstrap:5.3.3")
            dependency("org.webjars.npm:bootstrap-icons:1.11.3")
        }
    }

    // 强制排除未使用的库
    configurations.configureEach {
        exclude(module = "android-json")
        exclude(module = "commons-logging")
        exclude(module = "commons-dbcp")
        exclude(module = "commons-dbcp2")
        exclude(module = "commons-fileupload")
        exclude(module = "j2objc-annotations")
        exclude(module = "junit")
        exclude(module = "junit-vintage-engine")
        exclude(module = "log4j")
        exclude(module = "slf4j-log4j12")
        exclude(module = "ehcache")
        exclude(module = "poi-ooxml-schemas")
        exclude(module = "jaxb-api")
        exclude(module = "validation-api")
        exclude(module = "javax.servlet-api")
        exclude(module = "javax.activation-api")
        exclude(module = "bcpkix-jdk15on")
        exclude(module = "bcprov-jdk15on")
        exclude(module = "bcutil-jdk15on")
        exclude(module = "shardingsphere-test-util")
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
