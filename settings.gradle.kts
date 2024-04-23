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

rootProject.name = "SearchForCheapFlights"
include(":app")
include(":remote")
include(":ui-component")
include(":feature:main-screen")
include(":searchflights-data")
include(":navigation")
include(":feature:search-feature")
include(":feature:selectcountry-feature")
include(":feature:allticket-feature")
