package dev.stupak.exception.base

abstract class BaseException(
    override val message: String?,
    val originalException: Throwable?,
) : Throwable(message)
