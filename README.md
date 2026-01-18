# 基础开发平台

这是一个一直在整理一直在重构的代码库，自娱自乐。

## 单体服务

| Server       | Port |
|--------------|------|
| admin-server | 8282 |
| app-server   | 8181 |

## 微服务

| Server         | Port | Consul Development Key                | Consul Production Key                  |
|----------------|------|---------------------------------------|----------------------------------------|
| gateway-server | 8181 | config/application-dev/gateway-server | config/application-prod/gateway-server |
| sba-server     | 8282 | config/application-dev/sba-server     | config/application-prod/sba-server     |
| auth-server    | 8383 | config/application-dev/auth-server    | config/application-prod/auth-server    |
| system-server  | 8484 | config/application-dev/system-server  | config/application-prod/system-server  |

## Gradle Wrapper

```bash
# 官方
distributionUrl=https\://services.gradle.org/distributions/gradle-8.14.3-bin.zip
# 腾讯云
distributionUrl=https\://mirrors.cloud.tencent.com/gradle//gradle-8.14.3-bin.zip
```

## Buildpack

### dependency-mapping

下载连接

```shell
https://packages.bell-sw.com/alpaquita/java/21.0.8+12/bellsoft-jre21.0.8+12-linux-aarch64-musl-lite.tar.gz
```

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

#### bellsoft/buildpacks.builder

https://github.com/bell-sw/buildpacks/liberica

| SHA256                                                           | URL                                                                                                        |
|------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| 51198b180dbfde5fd60b9e5f6603c4f21e813cf939ff531d6a5ee43d212ef4fb | https://packages.bell-sw.com/alpaquita/java/21.0.8+12/bellsoft-jre21.0.8+12-linux-aarch64-musl-lite.tar.gz |
| c67f917a0fb18d7adb27e44adcc0a705402157667df17e5d9e72741ec7c44f07 | https://packages.bell-sw.com/alpaquita/java/21.0.8+12/bellsoft-jre21.0.8+12-linux-x64-musl-lite.tar.gz     |

```bash
mkdir -p ./tools/buildpacks/bindings/dependency-mapping
echo "dependency-mapping" >> type
echo "https://elvea.cn/tools/buildpacks/bellsoft-jre21.0.8+12-linux-aarch64-musl-lite.tar.gz" >> 51198b180dbfde5fd60b9e5f6603c4f21e813cf939ff531d6a5ee43d212ef4fb
echo "https://elvea.cn/tools/buildpacks/bellsoft-jre21.0.8+12-linux-x64-musl-lite.tar.gz" >> c67f917a0fb18d7adb27e44adcc0a705402157667df17e5d9e72741ec7c44f07
```

#### Builders

| SHA256                                   | URL             |
|------------------------------------------|-----------------|
| bellsoft/buildpacks.builder:musl         | Alpaquita Linux |
| paketobuildpacks/builder-noble-java-tiny | Ubuntu 24.4     |
| paketobuildpacks/builder-jammy-java-tiny | Ubuntu 22.04    |
