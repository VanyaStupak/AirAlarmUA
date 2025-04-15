package dev.stupak.usecase.exception

class UnknownException(
    override val message: String? = null,
    originalException: Exception? = null,
) : BaseDomainException(message, originalException)
