package dev.stupak.welcome

sealed class WelcomeIntent {
    data class SetRegion(val region: String) : WelcomeIntent()
}