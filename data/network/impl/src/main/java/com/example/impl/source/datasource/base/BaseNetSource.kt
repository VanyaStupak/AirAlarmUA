package com.example.impl.source.datasource.base


import com.example.exception.ApiException
import com.example.exception.ConnectionApiException
import com.example.exception.NotAuthorizedException
import com.example.exception.ServerErrorApiException
import com.example.exception.UnknownApiException
import com.example.exception.isConnectionError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

internal abstract class BaseNetSource(
    val httpClient: HttpClient,
) {
    protected suspend inline fun <reified T> performRequest(
        errorTransformer: (error: Exception) -> Throwable = ::transformError,
        request: HttpClient.() -> HttpResponse,
    ): T {
        val response = httpClient.request()
        return if (response.status.isSuccess()) {
            if (T::class == String::class) {
                response.bodyAsText() as T
            } else {
                response.body()
            }
        } else {
            val requestUrl = response.request.url.toString()
            val httpCode = response.status.value
            when (response.status) {
                HttpStatusCode.Unauthorized,
                HttpStatusCode.Forbidden -> {
                    throw NotAuthorizedException(
                        requestUrl = requestUrl,
                        httpCode = httpCode,
                        message = response.bodyAsText(),
                    )
                }

                else -> {
                    throw ApiException(
                        requestUrl = requestUrl,
                        httpCode = httpCode,
                        message = response.bodyAsText(),
                    ).let { errorTransformer(it) }
                }
            }
        }
    }

    protected open fun transformError(exception: Exception): Exception {
        return when {
            exception.isConnectionError -> ConnectionApiException(exception)
            //3xx - responses
            exception is RedirectResponseException -> UnknownApiException()
            //4xx - responses
            exception is ClientRequestException -> UnknownApiException()
            //5xx - responses
            exception is ServerResponseException -> ServerErrorApiException(
                error = exception.message,
                statusCode = null,
                statusMessage = null,
                originException = exception,
            )

            else -> UnknownApiException()
        }
    }

    companion object {
    }
}
