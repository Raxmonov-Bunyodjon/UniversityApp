// 🔹 Pluginlarni qayerdan olishni belgilash
pluginManagement {
    repositories {
        google { // Google Maven repository
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()           // Maven Central repository
        gradlePluginPortal()     // Gradle Plugin Portal
    }
}

// 🔹 Dependencylar uchun repository sozlamasi
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Faqat shu yerda repository ishlaydi
    repositories {
        google()
        mavenCentral()
    }
}

// 🔹 Project nomi va modulni qo‘shish
rootProject.name = "University App"
include(":app")
