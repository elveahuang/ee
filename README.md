# 基础开发平台

这是一个一直在整理一直在重构的代码库，自娱自乐。

## 单体服务

| Server     | Port |
|------------|------|
| app-server | 8181 |

## 微服务

| Server         | Port | Consul Development Key                | Consul Production Key                  |
|----------------|------|---------------------------------------|----------------------------------------|
| gateway-server | 9191 | config/application-dev/gateway-server | config/application-prod/gateway-server |
| sba-server     | 9292 | config/application-dev/sba-server     | config/application-prod/sba-server     |
| auth-server    | 9393 | config/application-dev/auth-server    | config/application-prod/auth-server    |
| system-server  | 9494 | config/application-dev/system-server  | config/application-prod/system-server  |
| lxp-server     | 9595 | config/application-dev/lxp-server     | config/application-prod/lxp-server     |
