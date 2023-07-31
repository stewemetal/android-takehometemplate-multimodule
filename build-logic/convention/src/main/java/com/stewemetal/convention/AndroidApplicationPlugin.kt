import com.android.build.api.dsl.ApplicationExtension
import com.stewemetal.convention.configureKotlinAndroid
import com.tier.app.convention.ANDROID_TARGET_SDK_VERSION
import org.gradle.api.Plugin
import org.gradle.api.Project
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
        }
    }
}
