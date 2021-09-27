package com.ke.mvvm_base.domain

import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.mvvm_base.entity.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.util.*
import kotlin.random.Random

class GetPhotoListUseCase : GetDataListUseCase<String, Photo>(Dispatchers.IO) {
    override suspend fun execute(index: Int, parameters: String): ListResult<Photo> {

        delay(2000)

        val photoList = mutableListOf<Photo>()

        repeat(10) {
            photoList.add(
                Photo(
                    parameters + UUID.randomUUID().toString(),
                    getImageUrl(),
                    "2021-09-30 12:00:00"
                )
            )
        }

        return ListResult(
            list = photoList,
            over = index >= 10,
            errorMessage = "",
            canRetry = false
        )
    }


}

 val imageList = arrayOf(
    "https://img0.baidu.com/it/u=1545407773,1465015052&fm=26&fmt=auto&gp=0.jpg",
    "https://img1.baidu.com/it/u=1871560431,1109072488&fm=26&fmt=auto&gp=0.jpg",
    "https://img1.baidu.com/it/u=2943008315,111705080&fm=26&fmt=auto&gp=0.jpg"
)

fun getImageUrl(): String {
    val index = Random.nextInt(imageList.size - 1)
    return imageList[index]
}