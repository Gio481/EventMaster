plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

gradlePlugin {
    plugins {
        register("EMGradleConfigPlugin") {
            id = "EMGradleConfigPlugin"
            implementationClass = "EMGradleConfigPlugin"
        }
        register("EMComposePlugin") {
            id = "EMComposePlugin"
            implementationClass = "EMComposePlugin"
        }

        register("EMGradleExtensionPlugin") {
            id = "EMGradleExtensionPlugin"
            implementationClass = "EMGradleExtensionPlugin"
        }
    }
}