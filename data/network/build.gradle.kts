plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.network"

    defaultConfig {
        buildConfigField(
            "String",
            "API_KEY",
            "\"${project.findProperty("API_KEY")}\""
        )
        buildConfigField(
            "String",
            "BOT_TOKEN",
            "\"${project.findProperty("BOT_TOKEN")}\""
        )
    }
}