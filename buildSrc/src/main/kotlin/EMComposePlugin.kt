import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class EMComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add(impl, platform(getLibrary("compose-bom")))
                add(impl, platform(getLibrary("kotlin")))
                impl { getLibrary("ktx") }
                impl { getLibrary("compose.ui") }
                impl { getLibrary("compose.graphics") }
                impl { getLibrary("compose.preview") }
                impl { getLibrary("compose.material") }
                impl { getLibrary("compose.navigation") }
                debugImpl { getLibrary("compose.debug") }
                debugImpl { getLibrary("compose.manifest") }
                impl { getLibrary("coroutine.lifecycle.viewModel") }
                impl { getLibrary("coroutine.lifecycle.runtime") }
            }
        }
    }
}