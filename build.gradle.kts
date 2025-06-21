plugins {
    id("org.sonarqube") version "6.2.0.5505"
}

sonar {
    properties {
        property("sonar.projectKey", "ElsaAkhmatyanova_java-project-71")
        property("sonar.organization", "elsaakhmatyanova")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}