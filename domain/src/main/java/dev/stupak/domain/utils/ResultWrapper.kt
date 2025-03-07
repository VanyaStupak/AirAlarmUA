package dev.stupak.domain.utils

object ResultWrapper {
    suspend fun <T> wrap(block: suspend () -> T): Result<T> {
        return runCatching { block() }
    }
}