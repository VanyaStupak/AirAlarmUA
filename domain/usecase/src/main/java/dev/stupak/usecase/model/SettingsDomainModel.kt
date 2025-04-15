package dev.stupak.usecase.model

data class SettingsDomainModel(
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
