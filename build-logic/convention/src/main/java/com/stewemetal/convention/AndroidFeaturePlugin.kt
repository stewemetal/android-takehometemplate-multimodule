
import com.stewemetal.convention.configuration.Junit
import com.stewemetal.convention.configuration.KoinTest
import com.stewemetal.convention.configuration.KotestBundle
import com.stewemetal.convention.configuration.TestOrchestrator
import com.stewemetal.convention.configuration.Timber
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.stewemetal.takehometemplate.android.library")
                apply("com.stewemetal.takehometemplate.android.library.compose")
            }

            dependencies {
                add("implementation", project(":shell"))

                add("implementation", Timber)

                add("testImplementation", KotestBundle)
                add("testImplementation", kotlin("test"))
                add("testImplementation", Junit)
                add("testImplementation", KoinTest)

                add("androidTestUtil", TestOrchestrator)
            }
        }
    }
}
