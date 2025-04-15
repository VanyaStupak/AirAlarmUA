package dev.stupak.local.model

data class SettingsDataStoreModel(
    val notifications: Boolean = false,
    val alertsNotifications: Boolean = false,
    val telegramNotifications: Boolean = false,
    val theme: Theme = Theme.AUTO,
    val region: String = "",
) {
    enum class Theme {
        AUTO,
        LIGHT,
        DARK,
    }
}
