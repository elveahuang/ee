plugins {
    id("idea")
    id("java")
    id("application")
    id("java-library")
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.spring.boot).apply(false)
    alias(libs.plugins.gradle.spotbugs).apply(false)
    alias(libs.plugins.gradle.native).apply(false)
    alias(libs.plugins.gradle.osdetector).apply(false)
}

allprojects {
    apply(plugin = "application")
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
        gradlePluginPortal()
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
            mavenBom(rootProject.libs.spring.ai.bom.get().toString())
            mavenBom(rootProject.libs.spring.cloud.bom.get().toString())
            mavenBom(rootProject.libs.spring.ai.alibaba.bom.get().toString())
            mavenBom(rootProject.libs.spring.boot.admin.bom.get().toString())
            mavenBom(rootProject.libs.mybatis.plus.bom.get().toString())
            mavenBom(rootProject.libs.cosid.bom.get().toString())
            mavenBom(rootProject.libs.hutool.bom.get().toString())
            mavenBom(rootProject.libs.mcp.bom.get().toString())
            mavenBom(rootProject.libs.mockito.bom.get().toString())
            mavenBom(rootProject.libs.netty.bom.get().toString())
            mavenBom(rootProject.libs.groovy.bom.get().toString())
            mavenBom(rootProject.libs.kotlin.bom.get().toString())
            mavenBom(rootProject.libs.jsonschema.generator.bom.get().toString())
            mavenBom(rootProject.libs.spring.boot.bom.get().toString())
        }
        dependencies {
            dependency(rootProject.libs.nimbus.jose.jwt.get().toString())
            dependency(rootProject.libs.oauth2.oidc.sdk.get().toString())
            dependency(rootProject.libs.asm.get().toString())
            dependency(rootProject.libs.commons.csv.get().toString())
            dependency(rootProject.libs.commons.lang.get().toString())
            dependency(rootProject.libs.commons.text.get().toString())
            dependency(rootProject.libs.commons.codec.get().toString())
            dependency(rootProject.libs.commons.beanutils.get().toString())
            dependency(rootProject.libs.checker.qual.get().toString())
            dependency(rootProject.libs.objenesis.get().toString())
            dependency(rootProject.libs.guava.get().toString())
            dependency(rootProject.libs.json.get().toString())
            dependency(rootProject.libs.annotations.get().toString())
            dependency(rootProject.libs.spotbugs.annotations.get().toString())
            dependency(rootProject.libs.httpcore.core.get().toString())
            dependency(rootProject.libs.httpcore.nio.get().toString())
            dependency(rootProject.libs.httpclient.get().toString())
            dependency(rootProject.libs.httpasyncclient.get().toString())
            dependency(rootProject.libs.httpmime.get().toString())
        }
    }

    configurations.configureEach {
        // 强制使用指定版本
        resolutionStrategy.eachDependency {
            if (requested.group == "org.seleniumhq.selenium") {
                useVersion(rootProject.libs.versions.seleniumVersion.get())
            }
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion(rootProject.libs.versions.kotlinVersion.get())
            }
            if (requested.group == "org.apache.groovy") {
                useVersion(rootProject.libs.versions.groovyVersion.get())
            }
            if (requested.group == "org.mockito") {
                useVersion(rootProject.libs.versions.mockitoVersion.get())
            }
            if (requested.group == "com.squareup.okhttp3") {
                useVersion(rootProject.libs.versions.okhttpVersion.get())
            }
            if (requested.group == "io.swagger.core.v3") {
                useVersion(rootProject.libs.versions.swaggerVersion.get())
            }
            if (requested.group == "org.bouncycastle") {
                useVersion(rootProject.libs.versions.bouncycastleVersion.get())
            }
            if (requested.module.toString() == "io.netty:netty-resolver-dns-native-macos") {
                this.artifactSelection {
                    this.selectArtifact(DependencyArtifact.DEFAULT_TYPE, null, "osx-aarch_64")
                }
            }
        }

        // 强制排除未使用的库
        exclude(group = "dom4j")
        exclude(group = "com.github.docker-java")
        exclude(group = "org.openoffice")
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
        exclude(module = "shardingsphere-test-util")
        exclude(module = "shardingsphere-infra-expr-groovy")
        exclude(module = "shardingsphere-infra-database-clickhouse")
        exclude(module = "shardingsphere-infra-database-firebird")
        exclude(module = "shardingsphere-infra-database-mariadb")
        exclude(module = "shardingsphere-infra-database-sql92")
        exclude(module = "shardingsphere-infra-database-p6spy")
        exclude(module = "shardingsphere-infra-database-sqlserver")
        exclude(module = "shardingsphere-infra-database-oracle")
        exclude(module = "shardingsphere-infra-database-opengauss")
        exclude(module = "shardingsphere-infra-database-h2")
        exclude(module = "shardingsphere-parser-sql-firebird")
        exclude(module = "shardingsphere-parser-sql-oracle")
        exclude(module = "shardingsphere-parser-sql-opengauss")
        exclude(module = "shardingsphere-parser-sql-sql92")
        exclude(module = "shardingsphere-parser-sql-sqlserver")
        exclude(module = "shardingsphere-parser-sql-h2")
        exclude(module = "shardingsphere-parser-sql-statement-clickhouse")
        exclude(module = "shardingsphere-parser-sql-statement-doris")
        exclude(module = "shardingsphere-parser-sql-statement-firebird")
        exclude(module = "shardingsphere-parser-sql-statement-hive")
        exclude(module = "shardingsphere-parser-sql-statement-opengauss")
        exclude(module = "shardingsphere-parser-sql-statement-oracle")
        exclude(module = "shardingsphere-parser-sql-statement-presto")
        exclude(module = "shardingsphere-parser-sql-statement-sql92")
        exclude(module = "shardingsphere-parser-sql-statement-sqlserver")
        exclude(module = "selenium-devtools-v136")
        exclude(module = "selenium-devtools-v137")
        exclude(module = "selenium-firefox-driver")
        exclude(module = "selenium-safari-driver")
        exclude(module = "selenium-ie-driver")
    }
}

subprojects {
    dependencies {
        implementation(rootProject.libs.bundles.baseCore)
        compileOnly(rootProject.libs.bundles.baseJakartaCompile)
        testImplementation(rootProject.libs.bundles.baseTest)
        annotationProcessor(rootProject.libs.bundles.baseAnnotationProcessor)
        testAnnotationProcessor(rootProject.libs.bundles.baseAnnotationProcessor)
    }
}
