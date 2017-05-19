import org.gradle.api.tasks.compile.GroovyCompile
import org.gradle.api.tasks.wrapper.Wrapper
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.1"
    id("com.gradle.plugin-publish") version "0.9.7"
}
apply {
    plugin("groovy")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())
    compileOnly(gradleScriptKotlinApi())
    compileOnly(kotlinModule("gradle-plugin", "1.1.1"))
}

// Allows us to use groovy code in kotlin
// https://discuss.gradle.org/t/kotlin-groovy-and-java-compilation/14903/10?u=jlleitschuh
val compileGroovy : GroovyCompile by tasks
val compileJava by tasks
compileGroovy.setDependsOn(compileGroovy.taskDependencies.getDependencies(compileGroovy) - compileJava)
val compileKotlin : AbstractKotlinCompile<*> by tasks
compileKotlin.dependsOn(compileGroovy)
compileKotlin.classpath += files(compileGroovy.destinationDir!!)
tasks.getByName("classes").dependsOn(compileKotlin)


task<Wrapper>("wrapper") {
    gradleVersion = "3.5"
}