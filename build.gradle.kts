plugins {
    application
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"

}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.1")
    implementation("org.xerial:sqlite-jdbc:3.53.4.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
}
application {
    mainClass = "com.herschaft.expenses.Main"
}

tasks.test {
    useJUnitPlatform()
}
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}