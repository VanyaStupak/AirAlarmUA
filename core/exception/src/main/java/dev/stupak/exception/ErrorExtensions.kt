package dev.stupak.exception

import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

val Throwable?.isConnectionError: Boolean
    get() =
        this is ConnectException ||
            this is UnknownHostException ||
            this is SocketException ||
            this is SocketTimeoutException ||
            (this?.cause?.isConnectionError ?: false)
