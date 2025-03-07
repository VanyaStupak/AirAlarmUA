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
        maven("https://jitpack.io")
    }
}

rootProject.name = "AirAlarmUA"
include(":app")

include(":data")
include(":domain")
include(":presentation")
include(":feature")
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
