import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("java")
    id("io.spring.dependency-management")
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
    options.compilerArgs.add("-Xlint:unchecked")
}

tasks.withType<Jar> {
    exclude("application.yml")
    exclude("application-*.yml")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

dependencyManagement {
    imports {
        mavenBom(libs.spring.ai.bom.get().toString())
        mavenBom(libs.spring.ai.extensions.bom.get().toString())
        mavenBom(libs.spring.ai.alibaba.bom.get().toString())
        mavenBom(libs.spring.boot.dependencies.get().toString())
        mavenBom(libs.spring.cloud.dependencies.get().toString())
        mavenBom(libs.spring.grpc.dependencies.get().toString())
        mavenBom(libs.spring.shell.dependencies.get().toString())
        mavenBom(libs.spring.modulith.bom.get().toString())
        mavenBom(libs.spring.swagger.bom.get().toString())
        mavenBom(libs.spring.boot.admin.bom.get().toString())
        mavenBom(libs.mybatis.plus.bom.get().toString())
        mavenBom(libs.cosid.bom.get().toString())
        mavenBom(libs.hutool.bom.get().toString())
        mavenBom(libs.mockito.bom.get().toString())
        mavenBom(libs.netty.bom.get().toString())
        mavenBom(libs.groovy.bom.get().toString())
        mavenBom(libs.kotlin.bom.get().toString())
        mavenBom(libs.jsonschema.generator.bom.get().toString())
        mavenBom(libs.grpc.bom.get().toString())
        mavenBom(libs.okhttp.bom.get().toString())
        mavenBom(libs.retrofit.bom.get().toString())
        mavenBom(libs.jackson.bom.get().toString())
        mavenBom(libs.bouncycastle.bom.get().toString())
        mavenBom(libs.embabel.agent.dependencies.get().toString())
        mavenBom(libs.agentscope.bom.get().toString())
    }
    dependencies {
        dependency(libs.nimbus.jose.jwt.get().toString())
        dependency(libs.oauth2.oidc.sdk.get().toString())
        dependency(libs.asm.get().toString())
        dependency(libs.commons.csv.get().toString())
        dependency(libs.commons.lang.get().toString())
        dependency(libs.commons.text.get().toString())
        dependency(libs.commons.codec.get().toString())
        dependency(libs.commons.pool.get().toString())
        dependency(libs.commons.beanutils.get().toString())
        dependency(libs.objenesis.get().toString())
        dependency(libs.guava.get().toString())
        dependency(libs.json.get().toString())
        dependency(libs.spotbugs.annotations.get().toString())
        dependency(libs.httpcore.get().toString())
        dependency(libs.httpclient.get().toString())
        dependency(libs.httpmime.get().toString())
    }
}

configurations.configureEach {
    // 强制使用指定版本
    resolutionStrategy.eachDependency {
        // 固定MaxOS环境下的版本为 osx-aarch_64，不在使用默认的 x64 版本
        if (requested.module.toString() == "io.netty:netty-resolver-dns-native-macos") {
            this.artifactSelection {
                this.selectArtifact(DependencyArtifact.DEFAULT_TYPE, null, "osx-aarch_64")
            }
        }
        if (requested.group == "org.apache.lucene") {
            useVersion(libs.versions.luceneVersion.get())
        }
        if (requested.group == "io.netty") {
            useVersion(libs.versions.nettyVersion.get())
        }
        if (requested.group == "org.mockito") {
            useVersion(libs.versions.mockitoVersion.get())
        }
        if (requested.group == "org.seleniumhq.selenium") {
            useVersion(libs.versions.seleniumVersion.get())
        }
        if (requested.group == "org.jetbrains.kotlin") {
            useVersion(libs.versions.kotlinVersion.get())
        }
        if (requested.group == "org.apache.groovy") {
            useVersion(libs.versions.groovyVersion.get())
        }
        if (requested.group == "com.squareup.okhttp3") {
            useVersion(libs.versions.okhttpVersion.get())
        }
        if (requested.group == "io.swagger.core.v3") {
            useVersion(libs.versions.swaggerVersion.get())
        }
        if (requested.group == "org.bouncycastle") {
            useVersion(libs.versions.bouncycastleVersion.get())
        }
        if (requested.group == "org.apache.poi") {
            useVersion(libs.versions.poiVersion.get())
        }
        if (requested.module.toString() == "com.google.errorprone:error_prone_annotations") {
            useVersion(libs.versions.errorProneAnnotationsVersion.get())
        }
        if (requested.module.toString() == "org.checkerframework:checker-qual") {
            useVersion(libs.versions.checkerQualVersion.get())
        }
        if (requested.module.toString() == "com.auth0:java-jwt") {
            useVersion(libs.versions.javaJwtVersion.get())
        }
        if (requested.module.toString() == "org.jsoup:jsoup") {
            useVersion(libs.versions.jsoupVersion.get())
        }
        if (requested.module.toString() == "org.antlr:antlr-runtime") {
            useVersion(libs.versions.antlr3Runtime.get())
        }
        if (requested.module.toString() == "org.antlr:antlr4-runtime") {
            useVersion(libs.versions.antlr4Runtime.get())
        }
        if (requested.module.toString() == "io.opentelemetry.semconv:opentelemetry-semconv") {
            useVersion(libs.versions.opentelemetrySemconvVersion.get())
        }
    }

    // 强制排除未使用的库
    exclude(group = "dom4j")
    exclude(group = "com.github.docker-java")
    exclude(group = "org.openoffice")
    exclude(module = "groovy")
    exclude(module = "groovy-json")
    exclude(module = "android-json")
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
    exclude(module = "poi-ooxml-lite")
    exclude(module = "poi-ooxml-schemas")
    exclude(module = "jaxb-api")
    exclude(module = "validation-api")
    exclude(module = "javax.servlet-api")
    exclude(module = "javax.activation-api")
    exclude(module = "bcpkix-jdk15on")
    exclude(module = "bcprov-jdk15on")
    exclude(module = "bcutil-jdk15on")
    exclude(module = "telegrambots-webhook")
    exclude(module = "shardingsphere-database-connector-h2")
    exclude(module = "selenium-devtools-v145")
    exclude(module = "selenium-devtools-v146")
    exclude(module = "selenium-firefox-driver")
    exclude(module = "selenium-safari-driver")
    exclude(module = "selenium-ie-driver")
    exclude(module = "kotlin-stdlib-jdk7")
    exclude(module = "kotlin-stdlib-jdk8")
}

dependencies {
    implementation(libs.bundles.baseCore)
    compileOnly(libs.bundles.baseJakartaCompile)
    testImplementation(libs.bundles.baseTest)
    annotationProcessor(libs.bundles.baseAnnotationProcessor)
    testAnnotationProcessor(libs.bundles.baseAnnotationProcessor)
}
