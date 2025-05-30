dependencies {
    api("org.apache.poi:poi")
    api("org.apache.poi:poi-ooxml")
    api("org.apache.poi:poi-ooxml-lite")
    api("cn.idev.excel:fastexcel")
    api("org.apache.pdfbox:pdfbox")
    api("org.dromara:x-easypdf-pdfbox")
    api("net.coobird:thumbnailator")
    api("com.twelvemonkeys.imageio:imageio-jpeg")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-docs")
}
