plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.1")
    implementation("org.xerial:sqlite-jdbc:3.53.4.0")

application {
    mainClass = "com.herschaft.expenses.Main"
}

tasks.test {
    useJUnitPlatform()
}
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}