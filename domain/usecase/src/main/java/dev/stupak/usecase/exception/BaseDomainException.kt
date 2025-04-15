package dev.stupak.usecase.exception

import dev.stupak.exception.base.BaseException

sealed class BaseDomainException(
    override val message: String?,
    originalException: Exception?,
) : BaseException(message, originalException)
