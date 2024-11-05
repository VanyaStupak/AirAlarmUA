package dev.stupak.network.impl

import dev.stupak.network.BuildConfig
import dev.stupak.network.model.ActiveAlertsModel
import dev.stupak.network.service.AlarmApiService
import dev.stupak.network.service.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AlarmApiServiceImpl @Inject constructor(private val httpClient: HttpClient) : AlarmApiService {
    override fun getActiveAlerts(): Flow<ApiResult<ActiveAlertsModel>> = flow{
        emit(ApiResult.Loading())
        try {
            emit(ApiResult.Success(httpClient.get("alerts/active.json?token=${BuildConfig.API_KEY}").body()))
        }
        catch (e:Exception){
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }
}

