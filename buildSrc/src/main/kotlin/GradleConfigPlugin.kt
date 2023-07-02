import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleConfigPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.apply(mapOf("from" to "${target.rootDir}/buildSrc/commons.gradle"))
    }
}