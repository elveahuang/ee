// 基础配置
pluginManagement {
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
}
rootProject.name = "platform"
// ---------------------------------------------------------------------------------------------------------------------
// 基础框架
// ---------------------------------------------------------------------------------------------------------------------
// 公共模块
include("platform-commons:commons-core")
include("platform-commons:commons-core-starter")
// ---------------------------------------------------------------------------------------------------------------------
// 单体服务
// ---------------------------------------------------------------------------------------------------------------------
// 微服务-模块
include("platform-boot-modules:system:system-api")
include("platform-boot-modules:system:system-impl")
include("platform-boot-modules:security")
include("platform-boot-modules:lxp:lxp-api")
include("platform-boot-modules:lxp:lxp-impl")
// 单体服务-应用
include("platform-boot-server:app-server")
// ---------------------------------------------------------------------------------------------------------------------
// 微服务
// ---------------------------------------------------------------------------------------------------------------------
// 微服务-模块
include("platform-cloud-modules:system:system-api")
include("platform-cloud-modules:system:system-biz")
include("platform-cloud-modules:system:system-web")
include("platform-cloud-modules:lxp:lxp-api")
include("platform-cloud-modules:lxp:lxp-impl")
// 微服务-服务
include("platform-cloud-services:gateway-server")
include("platform-cloud-services:auth-server")
include("platform-cloud-services:sba-server")
include("platform-cloud-services:system-server")
include("platform-cloud-services:lxp-server")
