package com.ke.mvvm.base.domian

import com.ke.mvvm.base.data.ListResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class GetDataListUseCase<P, R>(private val coroutineDispatcher: CoroutineDispatcher) {


    suspend operator fun invoke(index: Int, parameters: P): ListResult<R> {
        return try {
            withContext(coroutineDispatcher) {
                return@withContext execute(index, parameters)
            }
        } catch (e: Exception) {
            //异常处理
            return ListResult(errorMessage = "好像发生了一些错误", canRetry = true, exception = e)
        }
    }


    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(index: Int, parameters: P): ListResult<R>

}