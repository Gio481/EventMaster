import org.gradle.api.Plugin
import org.gradle.api.Project

class EMGradleExtensionPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        target.extensions.create('configPlugin', EMGradleConfigPluginExtension, target)
    }
}