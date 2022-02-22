package com.ke.mvvm.base.data


sealed interface ViewStatus<out T> {

    /**
     * 加载
     */
    data class Loading<out T>(val previous: ViewStatus<T>? = null) : ViewStatus<T>

    /**
     * 显示内容
     */
    data class Content<out T>(val data: T) : ViewStatus<T>

    /**
     * 出错
     */
    data class Error(val retry:Boolean) : ViewStatus<Nothing>


}