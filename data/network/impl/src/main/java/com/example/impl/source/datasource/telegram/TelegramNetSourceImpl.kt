package com.example.impl.source.datasource.telegram

import android.util.Log
import com.example.impl.source.datasource.base.BaseNetSource
import dev.stupak.network.BuildConfig
import dev.stupak.network.source.datasource.telegram.TelegramNetSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import javax.inject.Inject

internal class TelegramNetSourceImpl
@Inject
constructor(
   httpClient: HttpClient
): BaseNetSource(httpClient), TelegramNetSource {
    private var lastUpdateId: Long? = null

    override suspend fun getTgAlerts(): List<String> {
        val messages = mutableListOf<String>()

        try {
            if (lastUpdateId == null) {
                val initialResponse: HttpResponse = performRequest {
                    get("https://api.telegram.org/bot${BuildConfig.BOT_TOKEN}/getUpdates")
                }
                if (initialResponse.status.value == 200) {
                    val jsonResponse: JsonObject = initialResponse.body()
                    val updates = jsonResponse["result"]?.jsonArray

                    if (!updates.isNullOrEmpty()) {
                        lastUpdateId = updates.maxOfOrNull { it.jsonObject["update_id"]?.jsonPrimitive?.longOrNull ?: 0L }
                    } else {
                        lastUpdateId = 0L
                    }
                }
                return emptyList()
            }

            val response: HttpResponse =  performRequest {
                get("https://api.telegram.org/bot${BuildConfig.BOT_TOKEN}/getUpdates") {
                    parameter("offset", lastUpdateId?.plus(1))
                }
            }

            if (response.status.value == 200) {
                val jsonResponse: JsonObject = response.body()
                val updates = jsonResponse["result"]?.jsonArray

                updates?.forEach { update ->
                    val updateObject = update.jsonObject
                    val updateId = updateObject["update_id"]?.jsonPrimitive?.longOrNull
                    val message = updateObject["message"]?.jsonObject
                    if (message != null) {
                        val text = message["text"]?.jsonPrimitive?.content
                        if (text != null) {
                            messages.add(text)
                        }
                    }

                    if (updateId != null && (lastUpdateId == null || updateId > lastUpdateId!!)) {
                        lastUpdateId = updateId
                    }
                }
            } else {
                Log.e("TelegramMessages", "Помилка: ${response.status}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return messages
    }
}