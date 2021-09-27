package com.ke.mvvm_base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm_base.databinding.ItemPhotoBinding
import com.ke.mvvm_base.entity.Photo

class GalleryAdapter : BaseViewBindingAdapter<Photo, ItemPhotoBinding>() {


    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemPhotoBinding {
        return ItemPhotoBinding.inflate(inflater, parent, false)

    }

    override fun bindItem(
        item: Photo,
        viewBinding: ItemPhotoBinding,
        viewType: Int,
        position: Int
    ) {
        Glide.with(viewBinding.image)
            .load(item.url)
            .into(viewBinding.image)

//        viewBinding.dateTime.text = item.dateTime
    }
}
