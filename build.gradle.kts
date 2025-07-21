plugins {
    id("idea")
    id("java")
    id("application")
    id("java-library")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.spotbugs") version "6.2.2" apply false
    id("org.springframework.boot") version "3.5.3" apply false
    id("org.graalvm.buildtools.native") version "0.10.6" apply false
}

allprojects {
    apply(plugin = "application")
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/spring") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<Jar> {
        exclude("application.yml")
        exclude("application-*.yml")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.ai:spring-ai-bom:1.0.0")
            mavenBom("com.alibaba.cloud.ai:spring-ai-alibaba-bom:1.0.0.2")
            mavenBom("de.codecentric:spring-boot-admin-dependencies:3.5.1")
            mavenBom("org.springframework.grpc:spring-grpc-dependencies:0.9.0")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2025.0.0")
            mavenBom("io.modelcontextprotocol.sdk:mcp-bom:0.10.0")
            mavenBom("cn.hutool:hutool-bom:5.8.39")
            mavenBom("me.ahoo.cosid:cosid-bom:2.13.3")
            mavenBom("org.mockito:mockito-bom:5.18.0")
            mavenBom("io.netty:netty-bom:4.1.122.Final")
            mavenBom("org.apache.groovy:groovy-bom:4.0.27")
            mavenBom("com.baomidou:mybatis-plus-bom:3.5.12")
            mavenBom("org.jetbrains.kotlin:kotlin-bom:2.2.0")
            mavenBom("com.github.victools:jsonschema-generator-bom:4.38.0")
        }
        dependencies {
            // spring
            dependency("org.springframework.retry:spring-retry:2.0.12")
            dependency("org.springframework.security:spring-security-oauth2-authorization-server:1.5.1")
            // mapstruct & lombok
            dependency("org.mapstruct:mapstruct:1.6.3")
            dependency("org.mapstruct:mapstruct-processor:1.6.3")
            dependency("org.projectlombok:lombok-mapstruct-binding:0.2.0")
            // openapi & swagger & springdoc
            dependency("io.swagger.core.v3:swagger-core-jakarta:2.2.34")
            dependency("io.swagger.core.v3:swagger-annotations:2.2.34")
            dependency("io.swagger.core.v3:swagger-annotations-jakarta:2.2.34")
            dependency("io.swagger.core.v3:swagger-models:2.2.34")
            dependency("io.swagger.core.v3:swagger-models-jakarta:2.2.34")
            dependency("org.springdoc:springdoc-openapi-starter-common:2.8.9")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
            dependency("org.springdoc:springdoc-openapi-starter-webflux-ui:2.8.9")
            // database & nosql
            dependency("org.mybatis:mybatis:3.5.19")
            dependency("org.mybatis:mybatis-spring:3.0.5")
            dependency("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.5")
            dependency("com.github.jsqlparser:jsqlparser:5.3")
            dependency("org.redisson:redisson:3.50.0")
            dependency("org.redisson:redisson-spring-data-35:3.50.0")
            dependency("org.redisson:redisson-spring-boot-starter:3.50.0")
            dependency("org.apache.shardingsphere:shardingsphere-jdbc:5.5.2")
            // others
            dependency("com.nimbusds:nimbus-jose-jwt:10.4")
            dependency("com.nimbusds:oauth2-oidc-sdk:11.26")
            dependency("org.jetbrains:annotations:26.0.2")
            dependency("com.github.spotbugs:spotbugs-annotations:4.9.3")
            dependency("ognl:ognl:3.4.7")
            dependency("org.ow2.asm:asm:9.8")
            dependency("joda-time:joda-time:2.14.0")
            dependency("com.maxmind.geoip2:geoip2:4.3.1")
            dependency("com.squareup.okio:okio:3.15.0")
            dependency("com.squareup.okhttp3:okhttp:5.1.0")
            dependency("com.squareup.okhttp3:okhttp-sse:5.0.0")
            dependency("com.squareup.okhttp3:logging-interceptor:5.0.0")
            dependency("com.squareup.retrofit2:retrofit:3.0.0")
            dependency("com.squareup.retrofit2:adapter-rxjava3:3.0.0")
            dependency("com.squareup.retrofit2:converter-jackson:3.0.0")
            dependency("com.google.guava:guava:33.4.8-jre")
            dependency("com.github.oshi:oshi-core-java11:6.8.2")
            dependency("net.java.dev.jna:jna-platform:5.17.0")
            dependency("net.logstash.logback:logstash-logback-encoder:8.1")
            dependency("com.github.loki4j:loki-logback-appender:2.0.0")
            dependency("net.bytebuddy:byte-buddy:1.17.6")
            dependency("net.bytebuddy:byte-buddy-agent:1.17.6")
            dependency("org.bouncycastle:bcpkix-jdk18on:1.81")
            dependency("org.bouncycastle:bcprov-jdk18on:1.81")
            dependency("org.bouncycastle:bcutil-jdk18on:1.81")
            dependency("org.shredzone.acme4j:acme4j-client:3.5.1")
            dependency("org.shredzone.acme4j:acme4j-smime:3.5.1")
            dependency("com.xuxueli:xxl-job-core:3.1.1")
            dependency("io.github.bonigarcia:webdrivermanager:6.2.0")
            dependency("com.bucket4j:bucket4j_jdk17-core:8.14.0")
            dependency("com.bucket4j:bucket4j_jdk17-redisson:8.14.0")
            dependency("com.microsoft.playwright:playwright:1.53.0")
            // json & protobuf
            dependency("org.json:json:20250517")
            dependency("com.alibaba:fastjson:2.0.57")
            dependency("com.google.code.gson:gson:2.13.1")
            dependency("com.google.protobuf:protobuf-java:4.31.1")
            // xml & pdf & office & image
            dependency("xalan:xalan:2.7.3")
            dependency("xerces:xercesImpl:2.12.2")
            dependency("cn.idev.excel:fastexcel:1.2.0")
            dependency("org.apache.poi:poi:5.4.1")
            dependency("org.apache.poi:poi-ooxml:5.4.1")
            dependency("org.apache.poi:poi-ooxml-lite:5.4.1")
            dependency("com.google.zxing:core:3.5.3")
            dependency("com.google.zxing:javase:3.5.3")
            dependency("org.jodconverter:jodconverter-local:4.4.9")
            dependency("org.jodconverter:jodconverter-local-lo:4.4.9")
            dependency("org.jodconverter:jodconverter-remote:4.4.9")
            dependency("org.jodconverter:jodconverter-spring-boot-starter:4.4.9")
            dependency("org.libreoffice:juh:24.8.4")
            dependency("org.libreoffice:jurt:24.8.4")
            dependency("org.libreoffice:ridl:24.8.4")
            dependency("org.libreoffice:unoil:24.8.4")
            dependency("org.dom4j:dom4j:2.2.0")
            dependency("org.apache.pdfbox:fontbox:3.0.5")
            dependency("org.apache.pdfbox:preflight:3.0.5")
            dependency("org.apache.pdfbox:pdfbox:3.0.5")
            dependency("org.apache.pdfbox:pdfbox-io:3.0.5")
            dependency("org.apache.pdfbox:pdfbox-tools:3.0.5")
            dependency("org.apache.pdfbox:pdfbox-debuger:3.0.5")
            dependency("org.dromara:x-easypdf-pdfbox:3.4.2")
            dependency("org.jsoup:jsoup:1.21.1")
            dependency("net.coobird:thumbnailator:0.4.20")
            dependency("com.twelvemonkeys.imageio:imageio-jpeg:3.12.0")
            // sdk
            dependency("io.minio:minio:8.5.17")
            dependency("com.larksuite.oapi:oapi-sdk:2.4.20")
            dependency("com.github.binarywang:weixin-java-channel:4.7.0")
            dependency("com.github.binarywang:weixin-java-cp:4.7.0")
            dependency("com.github.binarywang:weixin-java-miniapp:4.7.0")
            dependency("com.github.binarywang:weixin-java-mp:4.7.0")
            dependency("com.github.binarywang:weixin-java-open:4.7.0")
            dependency("com.github.binarywang:weixin-java-pay:4.7.0")
            dependency("org.telegram:telegrambots-client:9.0.0")
            dependency("org.telegram:telegrambots-abilities:9.0.0")
            dependency("org.telegram:telegrambots-springboot-longpolling-starter:9.0.0")
            dependency("com.openai:openai-java:2.12.4")
            // sdk - aliyun
            dependency("com.aliyun:dingtalk:2.2.29")
            dependency("com.aliyun:alimt20181012:1.5.1")
            dependency("com.aliyun:facebody20191230:5.1.2")
            dependency("com.aliyun:dysmsapi20170525:4.1.2")
            dependency("com.aliyun.oss:aliyun-sdk-oss:3.18.3")
            dependency("com.alibaba:dashscope-sdk-java:2.20.8")
            dependency("com.aliyun:aliyun-java-sdk-core:4.7.6")
            dependency("com.alipay.sdk:alipay-sdk-java:4.40.328.ALL")
            // sdk - tencent
            dependency("com.qcloud:cos_api:5.6.247")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-common:3.1.1303")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-kms:3.1.1301")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-sms:3.1.1281")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-tmt:3.1.1297")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-iai:3.1.1303")
            dependency("com.tencentcloudapi:tencentcloud-sdk-java-hunyuan:3.1.1292")
            // commons
            dependency("commons-io:commons-io:2.19.0")
            dependency("commons-net:commons-net:3.11.1")
            dependency("commons-codec:commons-codec:1.18.0")
            dependency("commons-beanutils:commons-beanutils:1.11.0")
            dependency("org.apache.commons:commons-pool2:2.12.1")
            dependency("org.apache.commons:commons-email:1.6.0")
            dependency("org.apache.commons:commons-csv:1.14.0")
            dependency("org.apache.commons:commons-text:1.13.1")
            dependency("org.apache.commons:commons-lang3:3.18.0")
            dependency("org.apache.commons:commons-compress:1.27.1")
            dependency("org.apache.commons:commons-collections4:4.5.0")
            // 辅助用于排除重复的依赖
            dependency("com.aliyun:tea-rpc:0.1.3")
            dependency("com.aliyun:tea-openapi:0.3.10")
            dependency("com.aliyun:tea-rpc-util:0.1.3")
            dependency("org.jodd:jodd-bean:5.1.6")
            dependency("org.jodd:jodd-core:5.3.0")
            dependency("org.jodd:jodd-http:6.3.0")
            dependency("org.jodd:jodd-util:6.3.0")
            dependency("org.objenesis:objenesis:3.4")
            dependency("org.antlr:antlr4-runtime:4.13.2")
            dependency("org.apache.httpcomponents:httpcore:4.4.16")
            dependency("org.apache.httpcomponents:httpcore-nio:4.4.16")
            dependency("org.apache.httpcomponents:httpmime:4.5.14")
            dependency("org.apache.httpcomponents:httpclient:4.5.14")
            dependency("com.thoughtworks.xstream:xstream:1.4.21")
            dependency("org.checkerframework:checker-qual:3.49.5")
            dependency("io.github.classgraph:classgraph:4.8.180")
        }
    }

    configurations.configureEach {
        // 强制使用指定版本
        resolutionStrategy.eachDependency {
            if (requested.group == "org.seleniumhq.selenium") {
                useVersion("4.34.0")
            }
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion("2.2.0")
            }
            if (requested.group == "org.apache.groovy") {
                useVersion("4.0.27")
            }
            if (requested.group == "org.mockito") {
                useVersion("5.18.0")
            }
        }

        // 强制排除未使用的库
        exclude(module = "groovy")
        exclude(module = "groovy-json")
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
        exclude(module = "slf4j-simple")
        exclude(module = "ehcache")
        exclude(module = "poi-ooxml-schemas")
        exclude(module = "jaxb-api")
        exclude(module = "validation-api")
        exclude(module = "javax.servlet-api")
        exclude(module = "javax.activation-api")
        exclude(module = "bcpkix-jdk15on")
        exclude(module = "bcprov-jdk15on")
        exclude(module = "bcutil-jdk15on")
        exclude(module = "telegrambots-webhook")
        // shardingsphere
        exclude(module = "shardingsphere-test-util")
        exclude(module = "shardingsphere-infra-expr-groovy")
        exclude(module = "shardingsphere-infra-database-clickhouse")
        exclude(module = "shardingsphere-infra-database-mariadb")
        exclude(module = "shardingsphere-infra-database-sql92")
        exclude(module = "shardingsphere-infra-database-sqlserver")
        exclude(module = "shardingsphere-infra-database-oracle")
        exclude(module = "shardingsphere-infra-database-opengauss")
        exclude(module = "shardingsphere-infra-database-h2")
        exclude(module = "shardingsphere-parser-sql-sql92")
        exclude(module = "shardingsphere-parser-sql-sqlserver")
        exclude(module = "shardingsphere-parser-sql-oracle")
        exclude(module = "shardingsphere-parser-sql-opengauss")
        exclude(module = "shardingsphere-parser-sql-h2")
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
