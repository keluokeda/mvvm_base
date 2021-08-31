package com.ke.mvvm.base.data

import java.lang.Exception

data class ListResult<out T>(
    val list: List<T> = emptyList(),
    val over: Boolean = false,
    val errorMessage: String = "",
    val canRetry: Boolean = false,
    val exception: Exception? = null
) {


    val isSuccess: Boolean
        get() = errorMessage.isEmpty()
}
