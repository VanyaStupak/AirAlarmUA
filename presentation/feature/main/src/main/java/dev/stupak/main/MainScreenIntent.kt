package dev.stupak.main

sealed class MainScreenIntent {
    data object LoadAlerts : MainScreenIntent()
}
