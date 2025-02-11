dependencies {
    api("com.alibaba:easyexcel")
    api("org.apache.poi:poi")
    api("org.apache.poi:poi-ooxml")
    api("org.apache.poi:poi-ooxml-lite")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-docs")
}
