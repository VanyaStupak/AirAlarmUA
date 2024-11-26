package dev.stupak.main.viewModel

import dev.stupak.main.model.UiAlertsList


sealed class MainScreenState {
    data object Loading : MainScreenState()
    data class Success(val data: UiAlertsList) : MainScreenState()
    data class Error(val error: String) : MainScreenState()
}