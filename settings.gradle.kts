@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TakeHomeTemplate"
include(":app")
include(":shell")
include(":library:design")
include(":feature:login:contract")
include(":feature:login:impl")
include(":feature:item-list:contract")
include(":feature:item-list:impl")
include(":feature:item-details:contract")
include(":feature:item-details:impl")
