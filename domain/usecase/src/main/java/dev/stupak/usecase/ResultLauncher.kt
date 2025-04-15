package dev.stupak.usecase

import dev.stupak.exception.ApiException
import dev.stupak.exception.ConnectionApiException
import dev.stupak.exception.ServerErrorApiException
import dev.stupak.exception.TimeoutApiException
import dev.stupak.exception.UnknownApiException
import dev.stupak.usecase.exception.BaseDomainException
import dev.stupak.usecase.exception.NetworkException
import dev.stupak.usecase.exception.ServerException
import dev.stupak.usecase.exception.UnknownException

suspend fun <T> resultLauncher(action: suspend () -> T): Result<T> =
    try {
        Result.success(action())
    } catch (e: Exception) {
        Result.failure(handleException(e))
    }

private fun handleException(cause: Throwable): BaseDomainException =
    when (cause) {
        is ApiException ->
            when (cause) {
                is ServerErrorApiException -> ServerException()
                is ConnectionApiException, is TimeoutApiException, is UnknownApiException -> NetworkException()
                else -> UnknownException()
            }
        //endregion api

        else -> UnknownException()
    }
