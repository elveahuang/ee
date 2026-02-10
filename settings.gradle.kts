// 基础配置
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public") }
    }
}
rootProject.name = "ee"
// -----------------------------------------------------------------------
// 基础框架
// -----------------------------------------------------------------------
// 公共模块
include("platform-commons:commons-core")
include("platform-commons:commons-core-starter")
include("platform-commons:commons-javacv")
include("platform-commons:commons-console")
include("platform-commons:commons-webapp")
// -----------------------------------------------------------------------
// 单体服务
// -----------------------------------------------------------------------
// 应用模块
include("platform-boot-modules:system:system-api")
include("platform-boot-modules:system:system-impl")
include("platform-boot-modules:system:system-security")
// 应用服务
include("platform-boot-server:admin-server")
include("platform-boot-server:app-server")
// -----------------------------------------------------------------------
// 微服务
// -----------------------------------------------------------------------
// 应用模块
include("platform-cloud-modules:system:system-api")
include("platform-cloud-modules:system:system-impl")
// 应用服务
include("platform-cloud-services:gateway-server")
include("platform-cloud-services:auth-server")
include("platform-cloud-services:sba-server")
include("platform-cloud-services:system-server")
