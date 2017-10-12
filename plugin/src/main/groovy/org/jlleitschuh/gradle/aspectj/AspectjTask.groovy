package org.jlleitschuh.gradle.aspectj

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

class AspectjTask extends DefaultTask {

    @InputFiles
    FileCollection classpath
    @InputFiles
    FileCollection aspectPath
    @Input
    Configuration ajcConfiguration
    @InputFiles
    FileCollection inpath

    @OutputDirectory
    File destDir

    @TaskAction
    void compile() {
        // This task is out of date. We don't know if classes have been removed so remove them all.
        project.delete(destDir.listFiles())

        ant.taskdef(resource: "org/aspectj/tools/ant/taskdefs/aspectjTaskdefs.properties",
                classpath: ajcConfiguration.asPath)

        ant.iajc(
                maxmem: "1024m", fork: "true", Xlint: "ignore",
                destDir: destDir.absolutePath,
                aspectPath: aspectPath.asPath,
                inpath: inpath.asPath,
                classpath: classpath.asPath,
                source: project.sourceCompatibility,
                target: project.targetCompatibility
        )
    }
}