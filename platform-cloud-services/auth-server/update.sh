#!/bin/sh
root=$(pwd)
echo "current workspace - $root"
# pnpm
rm -rf package-lock.json
rm -rf pnpm-lock.yaml
rm -rf node_modules
ncu -u
# 安装依赖
pnpm install
# 格式化代码
pnpm run prettier
# 构建
pnpm run build
