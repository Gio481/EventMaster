import org.gradle.api.Action
import org.gradle.api.Project

class EMGradleConfigPluginExtension {
    private Project project

    private EMGradleConfig config = new EMGradleConfig()

    EMGradleConfigPluginExtension(Project project) {
        this.project = project
    }

    void config(Action<EMGradleConfig> configAction) {
        // apply the provided configuration to config
        configAction.execute(config)

        // manipulate gradle build based on the provided configurations
        manipulate()
    }

    private void manipulate() {
        populateBuildTypes(config.buildTypes)
    }

    void populateBuildTypes(Set<String> types) {
        project.android {
            buildTypes {
                types.each { type ->
                    // create (if exists) the provided build type
                    def createdType = maybeCreate(type)

                    // specify fallback to _production in case some build type (e.x. _debug) does not exist in child modules
                    if (createdType.name != BuildTypes.PRODUCTION)
                        createdType.matchingFallbacks = [BuildTypes.PRODUCTION]
                }
            }
        }
    }
}