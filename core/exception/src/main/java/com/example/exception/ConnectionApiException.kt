package com.example.exception

class ConnectionApiException(
    val originException: Exception,
) : ApiException(cause = originException) {

    override fun toString(): String = "ConnectionApiException(originException=${
        originException::class.simpleName + ": " + originException.message
    })"
}
