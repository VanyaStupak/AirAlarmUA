package dev.stupak.usecase.exception

class ServerException(
    override val message: String? = null,
    originalException: Exception? = null,
) : BaseDomainException(message, originalException)
