buildscript {
    dependencies {
        classpath ("org.jlleitschuh.aspectj:kotlin-aspectj-weaver:+")
    }
}
apply {
    plugin("org.jlleitschuh.gradle.kotlin-aspectj")
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.2"
    distributionType = Wrapper.DistributionType.ALL
}

