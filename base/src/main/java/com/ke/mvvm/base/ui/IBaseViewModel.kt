package com.ke.mvvm.base.ui

import androidx.lifecycle.LiveData
import com.ke.mvvm.base.model.SnackbarAction

interface IBaseViewModel {

    /**
     * 显示Snackbar
     */
    val snackbarEvent: LiveData<SnackbarAction>

    /**
     * 加载状态是否显示
     */
    val loadingViewVisible: LiveData<Boolean>

    /**
     * 内容布局是否显示
     */
    val contentViewVisible: LiveData<Boolean>

    /**
     * 重试页面是否显示
     */
    val retryViewVisible: LiveData<Boolean>


    /**
     * 重试
     */
    fun retry()
}