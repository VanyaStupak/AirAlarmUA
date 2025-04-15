package dev.stupak.exception

open class ApiException(
    open val requestUrl: String? = null,
    open val httpCode: Int? = null,
    override val message: String? = null,
    override val cause: Throwable? = null,
) : AppException(message, cause)

data class NotAuthorizedException(
    override val requestUrl: String,
    override val httpCode: Int,
    override val message: String? = null,
    override val cause: Throwable? = null,
) : ApiException(requestUrl, httpCode, message, cause)
