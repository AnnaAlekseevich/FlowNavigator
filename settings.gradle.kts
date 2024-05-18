pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FlowNavigator"
include(":app")
include(":feature:auth:api")
include(":feature:auth:ui")
include(":feature:posts:ui")
include(":feature:posts:api")
include(":feature:blog:ui")
include(":feature:profile:ui")

include(":feature:main:ui")
include(":core:navigation:ui")
include(":core:navigation:api")
include(":core:navigation:impl")
include(":uikit")
