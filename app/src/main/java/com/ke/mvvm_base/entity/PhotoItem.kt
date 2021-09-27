package com.ke.mvvm_base.entity

sealed interface PhotoItem {

    data class Item(val photo: Photo) : PhotoItem

    data class DateTime(val dateTime: String) : PhotoItem
}