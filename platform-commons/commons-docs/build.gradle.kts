dependencies {
    api("org.apache.poi:poi")
    api("org.apache.poi:poi-ooxml")
    api("org.apache.poi:poi-ooxml-lite")
    api("cn.idev.excel:fastexcel")
    api("org.apache.pdfbox:pdfbox")
    api("org.dromara:x-easypdf-pdfbox")
    // image
    api("net.coobird:thumbnailator")
    api("com.twelvemonkeys.imageio:imageio-jpeg")
    api("com.twelvemonkeys.imageio:imageio-webp")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-docs")
}
