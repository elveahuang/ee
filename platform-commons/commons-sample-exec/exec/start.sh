#!/bin/sh

# 启动进程
echo Starting 8080 ...
java -version
java -classpath "./libs/*" cc.wdev.app.JavaCvApplication
