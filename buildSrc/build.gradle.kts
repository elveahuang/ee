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

}
