package com.ke.mvvm_base.ui

import com.ke.mvvm.base.ui.BaseRefreshAndLoadMoreViewModel
import com.ke.mvvm_base.domain.GetPhotoListUseCase
import com.ke.mvvm_base.entity.Photo

class GalleryViewModel : BaseRefreshAndLoadMoreViewModel<String, Photo>(GetPhotoListUseCase()) {
    override val parameters: String
        get() = "photo"

    init {
        loadData(true)
    }
}