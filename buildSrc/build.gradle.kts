plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

gradlePlugin {
    plugins {
        register("GradleConfigPlugin") {
            id = "GradleConfigPlugin"
            implementationClass = "GradleConfigPlugin"
        }
        register("GradleDependenciesPlugin") {
            id = "GradleDependenciesPlugin"
            implementationClass = "GradleDependenciesPlugin"
        }
    }
}