package org.jlleitschuh.gradle.aspectj

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.compile.AbstractCompile
import org.gradle.api.tasks.compile.JavaCompile

/**
 * Allows us to compile code with the normal gradle compilers.
 * Then applies the aspectj compiler to do class time weaving to the classes.
 */
open class AspectjPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply(JavaPlugin::class.java)
        val ajcConfiguration = project.configurations.maybeCreate("ajc")
        val preWeaveJavaDir = project.file("${project.buildDir}/classes/java/preWeave")
        val preWeaveKotlinDir = project.file("${project.buildDir}/classes/kotlin/preWeave")

        val compileJava = (project.tasks.getByName("compileJava") as JavaCompile)
        val oldCompileJavaDestinationDir = compileJava.destinationDir
        compileJava.apply {
            destinationDir = preWeaveJavaDir
        }

        val sourceSets = project.convention.findPlugin(JavaPluginConvention::class.java)!!.sourceSets
        val mainSourceSet = sourceSets.getByName("main")
        val testSourceSet = sourceSets.getByName("test")
        val integTestSourceSet = sourceSets.findByName("integTest")
        /*
         * This is a workaround for this:
         * https://youtrack.jetbrains.com/issue/KT-17035
         */
        // In unit tests, link against the classes that haven't been weaved yet
//        testSourceSet.compileClasspath -= project.files(mainSourceSet.output.classesDirs)
//        testSourceSet.compileClasspath += project.files(preWeaveJavaDir)
        /*
         * In the integration tests we want to make sure that we are using the aspectj weaved classes.
         * We need to undo what we just did for integTestSourceSet because the above configuration is inherited.
         */
        integTestSourceSet?.apply { compileClasspath -= project.files(preWeaveJavaDir) }
        integTestSourceSet?.apply { compileClasspath += project.files(mainSourceSet.output.classesDir) }
        project.afterEvaluate {
            val compileKotlin = (project.tasks.findByName("compileKotlin") as? AbstractCompile)
            compileKotlin?.apply {
                destinationDir = preWeaveKotlinDir
            }

            val aspectConfiguration = project.configurations.maybeCreate("aspects")

            val compileAspect = project.taskHelper<AspectjTask>("compileAspect") {
                inpath = project.files(preWeaveJavaDir, preWeaveKotlinDir)
                this.ajcConfiguration = ajcConfiguration
                aspectPath = aspectConfiguration.asFileTree
                classpath = mainSourceSet.compileClasspath
                destDir = oldCompileJavaDestinationDir
                dependsOn(setOf(compileJava, compileKotlin).filterNotNull())
            }

            val classesTask = project.tasks.getByName("classes")
            classesTask.dependsOn(compileAspect)
        }
    }


    inline private fun <reified T : Task> Project.taskHelper(name: String, noinline configuration: T.() -> Unit): T {
        return this.tasks.create(name, T::class.java, configuration)!!
    }
}