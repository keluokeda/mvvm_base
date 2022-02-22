package com.ke.mvvm.base.ui

import com.ke.mvvm.base.data.ViewStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseContentViewModel<T> : BaseViewModel(), IBaseContentViewModel<T> {


    protected open val initialViewStatus: ViewStatus<T> = ViewStatus.Loading(null)

    private val _viewStatus: MutableStateFlow<ViewStatus<T>> by lazy {
        MutableStateFlow(initialViewStatus)
    }

    override val viewStatus: StateFlow<ViewStatus<T>>
        get() = _viewStatus

    protected fun showLoading() {
        val previous = _viewStatus.value
        _viewStatus.value = ViewStatus.Loading(previous)
    }

    protected fun showError(retry: Boolean) {
        _viewStatus.value = ViewStatus.Error(retry)
    }

    protected fun showContent(content: T) {
        _viewStatus.value = ViewStatus.Content(content)
    }


}