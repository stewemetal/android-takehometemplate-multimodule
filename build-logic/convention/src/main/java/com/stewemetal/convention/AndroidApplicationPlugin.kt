
import com.android.build.api.dsl.ApplicationExtension
import com.stewemetal.convention.configuration.AndroidTargetSdkVersion
import com.stewemetal.convention.configuration.TestOrchestrator
import com.stewemetal.convention.configuration.Timber
import com.stewemetal.convention.configuration.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.getByType<ApplicationExtension>().apply {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AndroidTargetSdkVersion

                buildFeatures.buildConfig = true
            }

            dependencies {
                add("implementation", Timber)

                add("androidTestUtil", TestOrchestrator)
            }
        }
    }
}
