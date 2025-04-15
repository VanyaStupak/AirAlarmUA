package dev.stupak.platform.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {
    protected fun <T> launch(
        coroutineContext: CoroutineDispatcher = Dispatchers.IO,
        coroutineScope: CoroutineScope = viewModelScope,
        onLoading: (suspend (Boolean) -> Unit)? = null,
        onResult: (suspend (T?) -> Unit)? = null,
        onError: (suspend (Throwable) -> Unit)? = null,
        debounce: Long? = null,
        request: suspend CoroutineScope.() -> T?,
    ): Job {
        val exceptionHandler =
            CoroutineExceptionHandler { _, throwable ->
                Log.e("error", "coroutine error", throwable)
                coroutineScope.launch {
                    onError?.invoke(throwable)
                    onLoading?.invoke(false)
                }
            }
        return coroutineScope.launch(
            context = exceptionHandler + coroutineContext,
        ) {
            debounce?.let { delay(it) }
            onLoading?.invoke(true)
            withContext(coroutineContext) { request() }.apply {
                this.let { result ->
                    onResult?.invoke(result)
                    onLoading?.invoke(false)
                }
            }
        }
    }
}
