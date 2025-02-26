dependencies {
    api("org.springframework:spring-jdbc")
    api("org.springframework.data:spring-data-jdbc")
    api("org.springframework.data:spring-data-jpa")
    api("org.hibernate.orm:hibernate-core")
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("platform-commons-hibernate")
}
