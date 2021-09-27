package com.ke.mvvm_base.ui

import com.ke.mvvm.base.ui.BaseRefreshAndLoadMoreViewModel
import com.ke.mvvm_base.domain.GetPhotoItemListUseCase
import com.ke.mvvm_base.entity.PhotoItem

class MultiItemViewModel :
    BaseRefreshAndLoadMoreViewModel<String, PhotoItem>(GetPhotoItemListUseCase()) {
    override val parameters: String
        get() = ""

    init {
        loadData(true)
    }
}