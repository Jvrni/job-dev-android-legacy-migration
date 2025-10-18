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
include(":feature")
include(":core:designSystem")
include(":core:navigation")
include(":core:common")
include(":feature:menu")
