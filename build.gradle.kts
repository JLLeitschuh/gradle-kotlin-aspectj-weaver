import org.gradle.api.tasks.wrapper.Wrapper

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.1"
    id("com.gradle.plugin-publish") version "0.9.7"
}


task<Wrapper>("wrapper") {
    gradleVersion = "3.5"
}