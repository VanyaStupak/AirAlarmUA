package dev.stupak.network.impl

import dev.stupak.network.BuildConfig
import dev.stupak.network.model.AlertsList
import dev.stupak.network.service.AlertsApiService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class AlertsApiServiceImpl @Inject constructor(private val httpClient: HttpClient) : AlertsApiService {

    override suspend fun getActiveAlertsInfo(): AlertsList {
        return httpClient.get("alerts/active.json?token=${BuildConfig.API_KEY}").body()
    }

    override suspend fun getAlertsForPeriod(uid: Int, period: String): AlertsList {
        return httpClient.get("regions/$uid/alerts/$period.json?token=${BuildConfig.API_KEY}").body()
    }
}






