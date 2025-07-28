# 镜像
FROM bellsoft/liberica-openjdk-rocky:21

#
RUN mkdir -p /app/logs
RUN mkdir -p /app/temp

#
WORKDIR /app

# 设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

ENTRYPOINT ["java", "-server", "-Xms512M", "-Xmx512M",
"-Djava.security.egd=file:/dev/./urandom",
"-Dfile.encoding=UTF-8",
 "-XX:+HeapDumpOnOutOfMemoryError", "-jar", "/app/app.jar" ]
