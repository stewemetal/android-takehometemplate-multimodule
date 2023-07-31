import com.android.build.api.dsl.LibraryExtension
import com.stewemetal.convention.configuration.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            extensions.getByType<LibraryExtension>().apply {
                configureAndroidCompose(this)
            }
        }
    }
}
