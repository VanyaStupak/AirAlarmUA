package dev.stupak.welcome

data class WelcomeState(
    val region: String = "",
    val isFirstRun: Boolean = true,
    val notifications: Boolean = false,
    val alertsNotifications: Boolean = false,
    val telegramNotifications: Boolean = false,
    val theme: Theme = Theme.AUTO,
) {
    enum class Theme {
        LIGHT,
        DARK,
        AUTO,
    }
}
