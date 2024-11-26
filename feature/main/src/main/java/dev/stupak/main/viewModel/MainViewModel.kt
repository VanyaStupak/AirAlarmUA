package dev.stupak.main.viewModel

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stupak.domain.usecase.GetActiveAlertsInfoUseCase
import dev.stupak.main.model.toUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getActiveAlertsUseCase: GetActiveAlertsInfoUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val uiState: StateFlow<MainScreenState> get() = _uiState

    init {
        startFetchingAlertsPeriodically()
    }

    fun onAction(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.LoadAlerts -> fetchAlerts()
        }
    }

    @SuppressLint("NewApi")
    private fun startFetchingAlertsPeriodically() {
        viewModelScope.launch {
            while (isActive) {
                fetchAlerts()
                delay(60 * 1000L)
            }
        }
    }

    @SuppressLint("NewApi")
    private fun fetchAlerts() {
        viewModelScope.launch {
            println("AAAAAAAAAAAAAAAAAAAAAAAAA")
            val result = getActiveAlertsUseCase.invoke()
            _uiState.value = when {
                result.isSuccess -> {
                    val data = result.getOrNull()?.toUiModel()
                    if (data != null) {
                        MainScreenState.Success(data)
                    } else {
                        MainScreenState.Error("No data available")
                    }
                }
                result.isFailure -> {
                    MainScreenState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
                }
                else -> MainScreenState.Error("Unexpected state")
            }
        }
    }
}
