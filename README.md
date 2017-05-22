# Kotlin AspectJ Weaver Gradle Plugin

[![Build Status](https://travis-ci.org/JLLeitschuh/gradle-kotlin-aspectj-weaver.svg?branch=master)](https://travis-ci.org/JLLeitschuh/gradle-kotlin-aspectj-weaver)

A Gradle plugin that allows you to weave Java and Kotlin files with AspectJ.

This plugin relies upon the kotlin and java plugins to compile your source files.
Then, this plugin uses class time weaving to weave your source code with aspects.

