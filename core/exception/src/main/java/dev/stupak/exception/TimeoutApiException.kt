package dev.stupak.exception

class TimeoutApiException(
    val originException: Exception? = null,
) : ApiException(cause = originException) {
    override fun toString(): String =
        "TimeoutApiException(originException=${
            originException?.let {
                it::class.simpleName + ": " + it.message
            } ?: "N/A"
        })"
}
