package com.example.exception

class ServerErrorApiException(
    val error: String?,
    val statusCode: Int?,
    val statusMessage: String?,
    val originException: Exception? = null,
) : ApiException(error, statusCode, statusMessage, originException) {

    override fun toString(): String = "ServerErrorApiException(error=$error, statusCode=${statusCode ?: "N/A"}, " +
        "statusMessage=$statusMessage, " +
        "originException=${
            originException?.let {
                it::class.simpleName + ": " + it.message
            } ?: "N/A"
        })"
}
