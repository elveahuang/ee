dependencies {
    api("org.mybatis:mybatis")
    api("org.mybatis:mybatis-spring")
    api("com.baomidou:mybatis-plus-annotation")
    api("com.baomidou:mybatis-plus-core")
    api("com.baomidou:mybatis-plus-extension")
    api("com.baomidou:mybatis-plus-jsqlparser")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-mybatis")
}
