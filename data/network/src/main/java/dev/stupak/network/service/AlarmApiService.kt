package dev.stupak.network.service

import dev.stupak.network.model.ActiveAlertsModel
import kotlinx.coroutines.flow.Flow

interface AlarmApiService {

     fun getActiveAlerts(): Flow<ApiResult<ActiveAlertsModel>>

}