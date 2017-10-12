import org.gradle.api.tasks.testing.logging.TestExceptionFormat

buildscript {
    dependencies {
        classpath("org.jlleitschuh.aspectj:kotlin-aspectj-weaver:+")
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.jlleitschuh.gradle.kotlin-aspectj")
        plugin("java")
    }
    val aspectJversion = "1.8.10"

    dependencies {
        "ajc"(create(group = "org.aspectj", name = "aspectjtools", version = aspectJversion))
        "compile"(create(group = "org.aspectj", name = "aspectjweaver", version = aspectJversion))
        "testCompile"(create(group = "junit", name = "junit", version = "4.12"))
    }

    tasks.withType<Test> {
        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.2"
    distributionType = Wrapper.DistributionType.ALL
}

