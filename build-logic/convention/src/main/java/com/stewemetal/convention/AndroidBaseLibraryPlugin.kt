import com.android.build.api.dsl.LibraryExtension
import com.stewemetal.convention.configuration.configureKotlinAndroid
import com.stewemetal.convention.configuration.ANDROID_TARGET_SDK_VERSION
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidBaseLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = ANDROID_TARGET_SDK_VERSION
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("junit").get())
            }
        }
    }
}
