package dev.stupak.exception

open class AppException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)
