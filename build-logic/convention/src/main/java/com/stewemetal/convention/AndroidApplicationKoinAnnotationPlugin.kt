
import com.stewemetal.convention.configuration.KoinAnnotations
import com.stewemetal.convention.configuration.KoinKspCompiler
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationKoinAnnotationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("implementation", KoinAnnotations)
                add("ksp", KoinKspCompiler)
            }
        }
    }
}
