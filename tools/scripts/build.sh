#!/bin/bash

ROOT=$(cd $(dirname $0);cd ../../; pwd)
echo $ROOT
cd $ROOT
chmod a+x ./gradlew
./gradlew clean bootJar

mkdir -p $ROOT/dist/boot

cp $ROOT/platform-boot-server/admin-server/build/libs/*.jar $ROOT/dist/boot
cp $ROOT/platform-boot-server/app-server/build/libs/*.jar $ROOT/dist/boot
