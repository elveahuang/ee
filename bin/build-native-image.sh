#!/bin/sh
root=$(pwd)
echo "current workspace - $root"
#
rm -rf native
#
mkdir -p native
#
cd native
#
jar -xvf ../boot-0.0.1.jar
#
native-image -H:Name=app -cp .:BOOT-INF/classes:`find BOOT-INF/lib | tr '\n' ':'`
#
mv app ../
