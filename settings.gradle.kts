rootProject.name = "km-chat-backend"

pluginManagement {
    plugins {
        kotlin("jvm") version "1.8.21"
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":km-chat-common")