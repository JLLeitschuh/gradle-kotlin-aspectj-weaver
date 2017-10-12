# Kotlin AspectJ Weaver Gradle Plugin

[![Build Status](https://travis-ci.org/JLLeitschuh/gradle-kotlin-aspectj-weaver.svg?branch=master)](https://travis-ci.org/JLLeitschuh/gradle-kotlin-aspectj-weaver)

A Gradle plugin that allows you to weave Java and Kotlin files with AspectJ
using class time weaving instead of compile time weaving.

A project loosely based upon the
[eveoh/gradle-aspectj](https://github.com/eveoh/gradle-aspectj)
project.
This project supports weaving just Java classes or both Java and Kotlin code.

This plugin could theoretically be adapted to weave other JVM languages but
currently only supports Java.


## Building Code

This build uses Gradle's new
[composite build](https://docs.gradle.org/4.1/userguide/composite_builds.html)
feature to integration test several subprojects under the `samples` directory.

If you want to run the samples and develop the plugin iteratively, then
import the `samples/settings.gradle` file into ItelliJ.
IntelliJ will figure out the rest.
