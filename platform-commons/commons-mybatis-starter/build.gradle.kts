dependencies {
    api(project(":platform-commons:commons-mybatis"))
    api("org.springframework.boot:spring-boot-starter-jdbc")
    api("com.baomidou:mybatis-plus-spring-boot3-starter")
    api("org.mybatis.spring.boot:mybatis-spring-boot-starter")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-mybatis-starter")
}
