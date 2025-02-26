dependencies {
    api("org.apache.poi:poi")
    api("org.apache.poi:poi-ooxml")
    api("org.apache.poi:poi-ooxml-lite")
    api("com.alibaba:easyexcel")
    api("org.apache.pdfbox:pdfbox")
    api("org.dromara:x-easypdf-pdfbox")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-docs")
}
