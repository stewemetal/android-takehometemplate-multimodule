package com.stewemetal.dependencychecker

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.artifacts.dependencies.DefaultProjectDependency

class DependencyCheckerPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            allprojects.forEach { module ->
                module.afterEvaluate {
                    when {
                        isApp() -> {
                            /* There are no restrictions on the `:app` module. */
                        }

                        isFeatureImplementation() -> {
                            verifyFeatureImplementationModuleDependencies(this)
                        }

                        isFeatureContract() -> {
                            verifyFeatureContractModuleDependencies(this)
                        }

                        isLibrary() -> {
                            verifyLibraryModuleDependencies(this)
                        }

                        isShell() -> {
                            verifyShellModuleDependencies(this)
                        }
                    }
                }
            }
        }
    }

    private fun verifyFeatureImplementationModuleDependencies(module: Project) {
        verifyModuleDependencies(module) { dependentModule ->
            dependentModule.isApp() ||
                dependentModule.isFeatureImplementation()
        }
    }

    private fun verifyFeatureContractModuleDependencies(module: Project) {
        verifyModuleDependencies(module) { dependentModule ->
            dependentModule.isApp() ||
                dependentModule.isFeatureImplementation() ||
                dependentModule.isFeatureContract() ||
                dependentModule.isLibrary()
        }
    }

    private fun verifyLibraryModuleDependencies(module: Project) {
        verifyModuleDependencies(module) { dependentModule ->
            dependentModule.isApp() ||
                dependentModule.isFeatureImplementation() ||
                dependentModule.isFeatureContract()
        }
    }

    private fun verifyShellModuleDependencies(module: Project) {
        verifyModuleDependencies(module) { dependentModule ->
            !dependentModule.displayName.contains(":design")
        }
    }

    private fun verifyModuleDependencies(module: Project, predicate: (Project) -> Boolean) {
        val invalidDependencies = module.dependentModules().filter(predicate)

        check(invalidDependencies.isEmpty()) {
            """${module.displayName} has the following invalid dependencies: 
                |${invalidDependencies.map { it.displayName }}
                |This is a project-level enforcement. 
                |See the architecture documentation in the project's README.
            """.trimMargin()
        }
    }
}

private fun Project.isApp(): Boolean {
    return name == "app"
}

private val featureRegex: Regex by lazy {
    "(?=.+:feature)(.+)(?<=:impl)".toRegex()
}

private fun Project.isFeatureImplementation(): Boolean {
    return displayName.contains(featureRegex)
}

private val featureContractRegex: Regex by lazy {
    "(?=.+:feature)(.+)(?<=:contract)".toRegex()
}

private fun Project.isFeatureContract(): Boolean {
    return displayName.contains(featureContractRegex)
}

private val libraryRegex by lazy {
    ":library:[A-Za-z0-9_-]+".toRegex()
}

private fun Project.isLibrary(): Boolean {
    return displayName.contains(libraryRegex)
}

private fun Project.isShell(): Boolean {
    return name == "shell"
}

private fun Project.dependentModules(): List<Project> {
    return configurations
        .filter { it.name == "api" || it.name == "implementation" }
        .flatMap { configuration ->
            configuration.dependencies
                .filterIsInstance<DefaultProjectDependency>()
                .map { it.dependencyProject }
        }
}
