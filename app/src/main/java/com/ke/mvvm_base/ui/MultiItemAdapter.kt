package com.ke.mvvm_base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm_base.databinding.ItemDateTimeBinding
import com.ke.mvvm_base.databinding.ItemPhotoBinding
import com.ke.mvvm_base.entity.PhotoItem

class MultiItemAdapter : BaseViewBindingAdapter<PhotoItem, ViewBinding>() {


    override fun getViewType(position: Int): Int {
        return when (getItem(position)) {
            is PhotoItem.DateTime -> TYPE_DATE_TIME
            is PhotoItem.Item -> TYPE_PHOTO
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return if (viewType == TYPE_PHOTO) {
            ItemPhotoBinding.inflate(inflater, parent, false)
        } else {
            ItemDateTimeBinding.inflate(inflater, parent, false)
        }
    }

    override fun bindItem(item: PhotoItem, viewBinding: ViewBinding, viewType: Int, position: Int) {
        when (item) {
            is PhotoItem.DateTime -> {
                (viewBinding as? ItemDateTimeBinding)?.apply {
                    dateTime.text = item.dateTime
                }
            }
            is PhotoItem.Item -> {
                (viewBinding as? ItemPhotoBinding)?.apply {
                    Glide.with(image)
                        .load(item.photo.url)
                        .into(image)
                }
            }
        }

        viewBinding.root.setOnClickListener {
            AlertDialog.Builder(it.context)
                .setTitle("点击了Item")
                .setMessage("位置=${position} item=${item}")
                .show()
        }
    }

    companion object {
        const val TYPE_PHOTO = 100
        const val TYPE_DATE_TIME = 200
    }
}