// 基础配置
pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/spring") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin") }
        gradlePluginPortal()
    }
}
rootProject.name = "ee"
// ---------------------------------------------------------------------------------------------------------------------
// 基础框架
// ---------------------------------------------------------------------------------------------------------------------
// 公共模块
include("platform-commons:commons-core")
include("platform-commons:commons-core-starter")
include("platform-commons:commons-hibernate")
include("platform-commons:commons-hibernate-starter")
include("platform-commons:commons-redisson")
include("platform-commons:commons-redisson-starter")
include("platform-commons:commons-mybatis")
include("platform-commons:commons-mybatis-starter")
include("platform-commons:commons-quartz")
include("platform-commons:commons-quartz-starter")
include("platform-commons:commons-rabbit")
include("platform-commons:commons-rabbit-starter")
include("platform-commons:commons-elastic")
include("platform-commons:commons-elastic-starter")
include("platform-commons:commons-mongo")
include("platform-commons:commons-mongo-starter")
include("platform-commons:commons-cosid")
include("platform-commons:commons-cosid-starter")
include("platform-commons:commons-oapis")
include("platform-commons:commons-oapis-starter")
include("platform-commons:commons-websocket")
include("platform-commons:commons-websocket-starter")
include("platform-commons:commons-security")
include("platform-commons:commons-security-starter")
include("platform-commons:commons-docs")
include("platform-commons:commons-docs-starter")
include("platform-commons:commons-ai")
include("platform-commons:commons-ai-starter")
// ---------------------------------------------------------------------------------------------------------------------
// 单体服务
// ---------------------------------------------------------------------------------------------------------------------
// 单体服务-模块
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
include("platform-cloud-modules:system:system-impl")
include("platform-cloud-modules:system:system-server")
include("platform-cloud-modules:lxp:lxp-api")
include("platform-cloud-modules:lxp:lxp-impl")
include("platform-cloud-modules:lxp:lxp-server")
// 微服务-基础服务
include("platform-cloud-services:gateway-server")
include("platform-cloud-services:auth-server")
include("platform-cloud-services:sba-server")
