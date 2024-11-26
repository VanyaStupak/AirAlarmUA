package dev.stupak.network.impl

import dev.stupak.network.BuildConfig
import dev.stupak.network.model.AlertsList
import dev.stupak.network.service.AlertsApiService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class AlertsApiServiceImpl @Inject constructor(private val httpClient: HttpClient) : AlertsApiService {
    override suspend fun getActiveAlerts(): Result<String> {
        return try {
            val response = httpClient.get("alerts/active.json?token=${BuildConfig.API_KEY}").body<String>()
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getActiveAlertsInfo(): Result<AlertsList> {
        return try {
            val response = httpClient.get("alerts/active.json?token=${BuildConfig.API_KEY}").body<AlertsList>()
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getAlertsForPeriod(uid: Int, period: String): Result<AlertsList> {
        return try {
            val response = httpClient.get("regions/$uid/alerts/$period.json?token=${BuildConfig.API_KEY}").body<AlertsList>()
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}






