package dev.stupak.network.source.datasource.alerts

import dev.stupak.network.model.AlertsList

interface AlertsNetSource {

    suspend fun getActiveAlertsInfo(): AlertsList

    suspend fun getAlertsForPeriod(uid :Int = 31,period: String = "month_ago"): AlertsList

}