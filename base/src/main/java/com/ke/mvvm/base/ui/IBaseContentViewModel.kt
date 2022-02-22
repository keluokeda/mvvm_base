package com.ke.mvvm.base.ui

import com.ke.mvvm.base.data.ViewStatus
import kotlinx.coroutines.flow.StateFlow

interface IBaseContentViewModel<T> : IBaseViewModel {

    val viewStatus: StateFlow<ViewStatus<T>>
}