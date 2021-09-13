package com.ke.mvvm_base

import com.ke.mvvm.base.data.Result
import com.ke.mvvm.base.domian.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowDemoUseCase : FlowUseCase<Int, Int>(Dispatchers.IO) {
    override fun execute(parameters: Int): Flow<Result<Int>> {
        return flow {
            emit(Result.Success(1 + parameters))
            kotlinx.coroutines.delay(2000)
            emit(Result.Success(2 + parameters))
        }
    }
}