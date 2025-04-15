package dev.stupak.usecase.exception

class NetworkException(
    override val message: String? = null,
    originalException: Exception? = null,
) : BaseDomainException(message, originalException)
