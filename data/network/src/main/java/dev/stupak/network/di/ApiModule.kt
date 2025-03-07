package dev.stupak.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.network.BuildConfig
import dev.stupak.network.impl.AlertsApiServiceImpl
import dev.stupak.network.impl.TelegramServiceImpl
import dev.stupak.network.service.AlertsApiService
import dev.stupak.network.service.TelegramService
import dev.stupak.network.utils.Const
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
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android){
            install(Logging){
                level= LogLevel.ALL
            }
            install(DefaultRequest){
                url(Const.BASE_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Api-Key", BuildConfig.API_KEY)
            }
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                })

            }
        }
    }

    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient): AlertsApiService = AlertsApiServiceImpl(httpClient)

    @Singleton
    @Provides
    fun provideTelegramService(httpClient: HttpClient): TelegramService = TelegramServiceImpl(httpClient)

}