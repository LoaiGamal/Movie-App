package com.example.android.imdbdemo.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel() : ViewModel(), CoroutineScope {

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()


    init {
        loadingLiveData.value = false
    }

    fun <T> callApiLiveData(request: suspend () -> T) = liveData<T>(Dispatchers.Main) {
        try {
            loadingLiveData.value = true
            val result = withContext(Dispatchers.IO) {
                request()
            }

            emit(result)

            loadingLiveData.value = false
        } catch (e: Exception) {
            loadingLiveData.value = false
            errorLiveData.value = e.localizedMessage
        }
    }

    fun handleApiCall(block: suspend CoroutineScope.() -> Unit) {
        launch {
            try {
                loadingLiveData.value = true

                block()

                loadingLiveData.value = false
            } catch (e: Exception) {
                loadingLiveData.value = false
                errorLiveData.value = e.localizedMessage
            }
        }
    }

}