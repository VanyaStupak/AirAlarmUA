package com.example.exception.base

abstract class BaseException(
    override val message: String?,
    val originalException: Throwable?,
) : Throwable(message)
