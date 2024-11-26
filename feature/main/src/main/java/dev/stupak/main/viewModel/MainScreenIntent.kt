package dev.stupak.main.viewModel

sealed class MainScreenIntent {
    data object LoadAlerts : MainScreenIntent()
}
