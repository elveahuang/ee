plugins {
    `kotlin-dsl`
}

repositories {
    maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public") }
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/google") }
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    // https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    // 插件必须在这里定义好依赖，否则在脚本里面会找不到插件
    implementation("io.spring.dependency-management:io.spring.dependency-management.gradle.plugin:${libs.versions.springDependencyManagementVersion.get()}")
    implementation("org.springframework.boot:org.springframework.boot.gradle.plugin:${libs.versions.springBootVersion.get()}")
    implementation("org.graalvm.buildtools.native:org.graalvm.buildtools.native.gradle.plugin:${libs.versions.gradleNativeVersion.get()}")
}
