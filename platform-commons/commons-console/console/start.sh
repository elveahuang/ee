#!/bin/sh

# 启动进程
echo Starting...
java -version
java -classpath "./libs/*:./libs-external/*" cc.wdev.sample.JavaCvApplication
