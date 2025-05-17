plugins {
    id("com.github.ben-manes.versions") version "0.50.0"
    application
    checkstyle
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}