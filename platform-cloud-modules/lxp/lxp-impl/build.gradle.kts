dependencies {
    // spring
    api("org.springframework:spring-webmvc")
    // modules
    api(project(":platform-commons:commons-core"))
    api(project(":platform-cloud-modules:lxp:lxp-api"))
    // mybatis
    api("org.mybatis:mybatis")
    api("org.mybatis:mybatis-spring")
    api("com.baomidou:mybatis-plus-annotation")
    api("com.baomidou:mybatis-plus-core")
    api("com.baomidou:mybatis-plus-extension")
    // jakarta
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}
