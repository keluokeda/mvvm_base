package com.ke.mvvm_base.ui

import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.ui.BaseContentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MineViewModel : BaseContentViewModel<UserInfo>() {

    private var index = 1

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            showLoading()
            delay(2000)
            showContent(
                UserInfo(
                    "汉库克", index, index, index, index
                )
            )
            index++
        }
    }
}