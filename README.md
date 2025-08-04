# 基础开发平台

这是一个一直在整理一直在重构的代码库，自娱自乐。

## 单体服务

| Server       | Port |
|--------------|------|
| app-server   | 8181 |
| admin-server | 8282 |

## 微服务

| Server         | Port | Consul Development Key                | Consul Production Key                  |
|----------------|------|---------------------------------------|----------------------------------------|
| gateway-server | 9191 | config/application-dev/gateway-server | config/application-prod/gateway-server |
| sba-server     | 9292 | config/application-dev/sba-server     | config/application-prod/sba-server     |
| auth-server    | 9393 | config/application-dev/auth-server    | config/application-prod/auth-server    |
| system-server  | 9494 | config/application-dev/system-server  | config/application-prod/system-server  |

## Gradle Wrapper

```bash
# 官方
distributionUrl=https\://services.gradle.org/distributions/gradle-8.14.3-bin.zip
# 腾讯云
distributionUrl=https\://mirrors.cloud.tencent.com/gradle//gradle-8.14.3-bin.zip
```

## Buildpack

### dependency-mapping

#### syft

https://github.com/paketo-buildpacks/syft

| SHA256                                                           | URL                                                                                      |
|------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| 95c8533c46d4a952a576365f373d04fc0a4348e7335cbb82ac3ea2bc66e1e58b | https://github.com/anchore/syft/releases/download/v1.28.0/syft_1.28.0_linux_arm64.tar.gz |
| 3edee7fe1ceb1f78360e547f57048930d57f00c7ec3d0b8bdfb902805f048468 | https://github.com/anchore/syft/releases/download/v1.28.0/syft_1.28.0_linux_amd64.tar.gz |

```bash
mkdir -p ./tools/buildpacks/bindings/dependency-mapping
echo "dependency-mapping" >> type
echo "https://elvea.cn/tools/buildpacks/syft_1.28.0_linux_amd64.tar.gz" >> 3edee7fe1ceb1f78360e547f57048930d57f00c7ec3d0b8bdfb902805f048468
echo "https://elvea.cn/tools/buildpacks/syft_1.28.0_linux_arm64.tar.gz" >> 95c8533c46d4a952a576365f373d04fc0a4348e7335cbb82ac3ea2bc66e1e58b
```

#### liberica

https://github.com/paketo-buildpacks/bellsoft-liberica

| SHA256                                                           | URL                                                                                                        |
|------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| 3a0ae444096c154d1bcbbacc90664464a63437adb01f7dc95b523e5d82074821 | https://github.com/bell-sw/Liberica/releases/download/21.0.8+12/bellsoft-jre21.0.8+12-linux-aarch64.tar.gz |
| 40db0d81616324c50186b374c16af77fc34f4e37b88c746c465d82d39f2dd8b5 | https://github.com/bell-sw/Liberica/releases/download/21.0.8+12/bellsoft-jre21.0.8+12-linux-amd64.tar.gz   |

```bash
mkdir -p ./tools/buildpacks/bindings/dependency-mapping
echo "dependency-mapping" >> type
echo "https://elvea.cn/tools/buildpacks/bellsoft-jre21.0.8+12-linux-amd64.tar.gz" >> 40db0d81616324c50186b374c16af77fc34f4e37b88c746c465d82d39f2dd8b5
echo "https://elvea.cn/tools/buildpacks/bellsoft-jre21.0.8+12-linux-aarch64.tar.gz" >> 3a0ae444096c154d1bcbbacc90664464a63437adb01f7dc95b523e5d82074821
```
