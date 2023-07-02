import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate

fun Project.libs(name: String = "libs"): VersionCatalog {
    return extensions.getByType<VersionCatalogsExtension>().named(name)
}

fun Project.getLibrary(name: String): Provider<MinimalExternalModuleDependency> {
    return libs().findLibrary(name).get()
}

fun DependencyHandlerDelegate.api(dependency: () -> Any) {
    add(api, dependency.invoke())
}

fun DependencyHandlerDelegate.impl(dependency: () -> Any) {
    add(impl, dependency.invoke())
}

fun DependencyHandlerDelegate.debugImpl(dependency: () -> Any) {
    add(debugImpl, dependency.invoke())
}

const val api = "api"

const val impl = "implementation"

const val debugImpl = "debugImplementation"