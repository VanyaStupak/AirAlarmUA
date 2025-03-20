package com.example.exception

open class AppException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)
