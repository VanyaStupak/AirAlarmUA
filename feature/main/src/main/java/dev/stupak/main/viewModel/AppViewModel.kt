package dev.stupak.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stupak.network.model.ActiveAlertsModel
import dev.stupak.network.service.AlarmApiService
import dev.stupak.network.service.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val apiService: AlarmApiService): ViewModel() {
    private val _alerts= MutableStateFlow<ApiResult<ActiveAlertsModel>>(ApiResult.Loading())
    val alerts= _alerts.asStateFlow()

    init {
        fetchQuotes()
    }

    private fun fetchQuotes(){
        viewModelScope.launch {
            apiService.getActiveAlerts()
                .flowOn(Dispatchers.IO)
                .catch {
                    _alerts.value=ApiResult.Error(it.message ?: "Something went wrong")
                }
                .collect{
                    _alerts.value=it
                }
        }
    }
}