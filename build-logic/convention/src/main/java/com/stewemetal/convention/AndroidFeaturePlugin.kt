import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply("com.stewemetal.calibrex.android.library")
                apply("com.stewemetal.calibrex.android.library.compose")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":shell"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("junit").get())
                add("testImplementation", libs.findLibrary("io.insert.koin.test").get())
            }
        }
    }
}
