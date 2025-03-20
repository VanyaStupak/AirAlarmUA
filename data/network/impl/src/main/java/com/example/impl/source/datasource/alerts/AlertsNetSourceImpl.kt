package com.example.impl.source.datasource.alerts

import com.example.impl.source.datasource.base.BaseNetSource
import dev.stupak.network.BuildConfig
import dev.stupak.network.model.AlertsList
import dev.stupak.network.source.datasource.alerts.AlertsNetSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class AlertsNetSourceImpl @Inject constructor(
   httpClient: HttpClient
) :BaseNetSource(httpClient), AlertsNetSource {

    override suspend fun getActiveAlertsInfo(): AlertsList {
        return performRequest {
            get("alerts/active.json?token=${BuildConfig.API_KEY}").body()
        }
    }

    override suspend fun getAlertsForPeriod(uid: Int, period: String): AlertsList {
        return performRequest {
            get("regions/$uid/alerts/$period.json?token=${BuildConfig.API_KEY}").body()
        }
    }
}






