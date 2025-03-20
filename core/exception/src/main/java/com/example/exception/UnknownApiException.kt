package com.example.exception

class UnknownApiException(
    val originException: Exception? = null,
) : ApiException(cause = originException) {
    override fun toString(): String = "ApiException(originException=${
        originException?.let {
            it::class.simpleName + ": " + it.message
        } ?: "N/A"
    })"
}
