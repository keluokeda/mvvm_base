package com.ke.mvvm_base.domain

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.mvvm_base.entity.Photo
import com.ke.mvvm_base.entity.PhotoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.util.*

class GetPhotoItemListUseCase : GetDataListUseCase<String, PhotoItem>(Dispatchers.IO) {
    override suspend fun execute(index: Int, parameters: String): ListResult<PhotoItem> {
        delay(2000)

        val photoList = mutableListOf<PhotoItem>()

        repeat(10) {

            val photo = Photo(
                parameters + UUID.randomUUID().toString(),
                getImageUrl(),
                "2021-09-30 12:00:00"
            )
            photoList.add(
                PhotoItem.Item(photo)
            )
            if (it % 3 == 0) {
                photoList.add(PhotoItem.DateTime(UUID.randomUUID().toString()))
            }
        }

        return ListResult(
            list = photoList,
            over = index >= 10,
            errorMessage = "",
            canRetry = false
        )
    }


}