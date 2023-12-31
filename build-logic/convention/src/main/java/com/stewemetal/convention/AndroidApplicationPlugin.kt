
import com.android.build.api.dsl.ApplicationExtension
import com.stewemetal.convention.configuration.ANDROID_TARGET_SDK_VERSION
import com.stewemetal.convention.configuration.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
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
                defaultConfig.targetSdk = target.ANDROID_TARGET_SDK_VERSION
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("androidTestUtil", libs.findLibrary("androidx-test-orchestrator").get())
            }
        }
    }
}
