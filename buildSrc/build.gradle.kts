plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

gradlePlugin {
    plugins {
        register("EMGradleSetUpPlugin") {
            id = "EMGradleSetUpPlugin"
            implementationClass = "EMGradleSetUpPlugin"
        }
        register("EMComposePlugin") {
            id = "EMComposePlugin"
            implementationClass = "EMComposePlugin"
        }

        register("EMGradleConfigExtensionPlugin") {
            id = "EMGradleConfigExtensionPlugin"
            implementationClass = "EMGradleConfigExtensionPlugin"
        }
    }
}