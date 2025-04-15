package dev.stupak.network.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.network.BuildConfig
import dev.stupak.network.impl.core.client.HttpRoutes
import dev.stupak.network.impl.source.datasource.alerts.AlertsNetSourceImpl
import dev.stupak.network.impl.source.datasource.telegram.TelegramNetSourceImpl
import dev.stupak.network.source.datasource.alerts.AlertsNetSource
import dev.stupak.network.source.datasource.telegram.TelegramNetSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient =
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(HttpRoutes.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Api-Key", BuildConfig.API_KEY)
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
        }

    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient): AlertsNetSource = AlertsNetSourceImpl(httpClient)

    @Singleton
    @Provides
    fun provideTelegramService(httpClient: HttpClient): TelegramNetSource = TelegramNetSourceImpl(httpClient)
}
