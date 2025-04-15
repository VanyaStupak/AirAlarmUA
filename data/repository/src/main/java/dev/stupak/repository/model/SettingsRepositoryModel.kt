package dev.stupak.repository.model

data class SettingsRepositoryModel(
    val notifications: Boolean,
    val alertsNotifications: Boolean,
    val telegramNotifications: Boolean,
    val region: String,
    val theme: Theme,
) {
    enum class Theme {
        AUTO,
        LIGHT,
        DARK,
    }
}
