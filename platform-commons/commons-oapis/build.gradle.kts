dependencies {
    api("io.minio:minio")
    api("com.github.binarywang:weixin-java-mp")
    api("com.github.binarywang:weixin-java-cp")
    api("com.github.binarywang:weixin-java-miniapp")
    api("com.aliyun:aliyun-java-sdk-core")
    api("com.aliyun:dingtalk")
    api("com.aliyun:facebody20191230")
    api("com.aliyun:dysmsapi20170525")
    api("com.aliyun:alimt20181012")
    api("com.aliyun.oss:aliyun-sdk-oss")
    api("com.qcloud:cos_api")
    api("com.tencentcloudapi:tencentcloud-sdk-java-common")
    api("com.tencentcloudapi:tencentcloud-sdk-java-sms")
    api("com.tencentcloudapi:tencentcloud-sdk-java-tmt")
    api("com.tencentcloudapi:tencentcloud-sdk-java-iai")
    api("com.larksuite.oapi:oapi-sdk")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-oapis")
}
