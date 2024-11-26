package dev.stupak.network.impl

import dev.stupak.network.service.TelegramService
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

class TelegramServiceImpl
@Inject
constructor(
    private val httpClient: HttpClient
): TelegramService {
    private var lastUpdateId: Long? = null

    override suspend fun getTgAlerts(): List<String> {
        val messages = mutableListOf<String>()

        try {

            if (lastUpdateId == null) {
                val initialResponse: HttpResponse = httpClient.get("https://api.telegram.org/bot7689621230:AAGodMHSmMIbzX54E6JBjPPp-fepLXoT_N4/getUpdates")
                if (initialResponse.status.value == 200) {
                    val jsonResponse: JsonObject = initialResponse.body()
                    val updates = jsonResponse["result"]?.jsonArray
                    lastUpdateId = updates?.maxOfOrNull { it.jsonObject["update_id"]?.jsonPrimitive?.longOrNull ?: 0L }
                }
                return emptyList()
            }

            val response: HttpResponse = httpClient.get("https://api.telegram.org/bot7689621230:AAGodMHSmMIbzX54E6JBjPPp-fepLXoT_N4/getUpdates") {
                parameter("offset", lastUpdateId?.plus(1))
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
                            messages.add("Повідомлення від користувача: $text")
                        }
                    }
                    // Обновляем lastUpdateId, если текущее значение больше
                    if (updateId != null && (lastUpdateId == null || updateId > lastUpdateId!!)) {
                        lastUpdateId = updateId
                    }
                }
            } else {
                println("Ошибка: ${response.status}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return messages
    }
}