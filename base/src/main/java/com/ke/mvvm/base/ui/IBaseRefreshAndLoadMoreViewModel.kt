package com.ke.mvvm.base.ui

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow

interface IBaseRefreshAndLoadMoreViewModel<R>:IBaseViewModel {

    /**
     * 要显示到页面上的数据
     */
    val dataList: StateFlow<List<R>?>


    /**
     * 加载更多结果
     * @see LOAD_DATA_RESULT_END
     * @see LOAD_DATA_RESULT_ERROR
     * @see LOAD_DATA_RESULT_SUCCESS
     */
    val loadDataResult: StateFlow<Int?>


    /**
     * 刷新数据
     */
    fun refresh()

    /**
     * 上拉加载
     */
    fun loadMore()

    /**
     * SwipeRefreshLayout刷新指示器是否可见
     */
    val isRefreshing: StateFlow<Boolean?>


    companion object {

        /**
         * 加载数据成功
         */
        const val LOAD_DATA_RESULT_SUCCESS = 0

        /**
         * 加载数据出错
         */
        const val LOAD_DATA_RESULT_ERROR = 1

        /**
         * 加载数据已完成，没有更多数据
         */
        const val LOAD_DATA_RESULT_END = 2
    }
}