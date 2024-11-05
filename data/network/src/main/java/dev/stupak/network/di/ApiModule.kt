package dev.stupak.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.stupak.network.BuildConfig
import dev.stupak.network.impl.AlarmApiServiceImpl
import dev.stupak.network.service.AlarmApiService
import dev.stupak.network.utils.Util
import io.ktor.client.HttpClient
import io.ktor.http.HttpHeaders
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
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
                url(Util.BASE_URL)
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
    fun provideApiService(httpClient: HttpClient): AlarmApiService = AlarmApiServiceImpl(httpClient)

}