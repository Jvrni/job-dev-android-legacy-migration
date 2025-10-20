rootProject.name = "CadapioAndroid"
include(":app")
pluginManagement {
    repositories {
        google()

        mavenCentral()
        gradlePluginPortal()
    }
}
include(":data")
include(":core:common")
include(":feature")
include(":feature:menu")
include(":core:designSystem")
include(":core:navigation")
include(":domain")
