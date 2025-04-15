pluginManagement {
    includeBuild("build-logic")
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
        maven("https://jitpack.io")
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        gradlePluginPortal()
    }
}

rootProject.name = "AirAlarmUA"
include(":app")

include(":data")
include(":domain")
include(":presentation")
include(":presentation:feature:welcome")
include(":presentation:feature:main")
include(":core")
include(":core:ui")
include(":data:network")
include(":data:repository")
include(":data:worker")
include(":presentation:feature:settings")
include(":core:common")
include(":data:local")
include(":presentation:feature:widget")
include(":data:database")
include(":presentation:feature:host")
include(":presentation:core:navigation")
include(":presentation:core:platform")
include(":presentation:core:worker")
include(":data:local:impl")
include(":data:network:impl")
include(":data:repository:impl")
include(":core:exception")
include(":data:database:impl")
include(":domain:usecase")
include(":domain:usecase:impl")
