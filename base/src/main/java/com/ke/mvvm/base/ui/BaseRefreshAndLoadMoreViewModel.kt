package com.ke.mvvm.base.ui

import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.ListResult
import com.ke.mvvm.base.domian.GetDataListUseCase
import com.ke.mvvm.base.model.SnackbarAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseRefreshAndLoadMoreViewModel<P, R>(private val getDataListUseCase: GetDataListUseCase<P, R>) :
    BaseViewModel(), IBaseRefreshAndLoadMoreViewModel<R> {

    private var currentIndex: Int = 0


    protected open val startIndex = 1

    /**
     * 请求参数
     */
    protected abstract val parameters: P
    protected val _dataList = MutableStateFlow<List<R>?>(null)

    override val dataList: StateFlow<List<R>?>
        get() = _dataList

    private val _isRefreshing = MutableStateFlow<Boolean?>(null)

    /**
     * 是否显示刷新指示器
     */
    override val isRefreshing: StateFlow<Boolean?>
        get() = _isRefreshing

    private val _loadDataResult = MutableStateFlow<Int?>(null)

    override val loadDataResult: StateFlow<Int?>
        get() = _loadDataResult


    override fun refresh() {
        loadData(true)
    }

    protected open fun loadData(forceRefresh: Boolean = false) {

        viewModelScope.launch {
            if (forceRefresh) {
                currentIndex = startIndex
                _isRefreshing.value = true

            }
            val result = getDataListUseCase.invoke(currentIndex, parameters)
            _isRefreshing.value = false

            if (result.isSuccess) {
                val currentList =
                    _dataList.value ?: emptyList()
                _dataList.value =
                    (if (forceRefresh) emptyList() else currentList) + result.list
                currentIndex++
                _loadDataResult.value =
                    if (result.over) LOAD_DATA_RESULT_END else LOAD_DATA_RESULT_SUCCESS

            } else {
                onLoadError(result)
                _loadDataResult.value = LOAD_DATA_RESULT_ERROR
            }


        }


    }

    /**
     * 加载数据出错
     */
    protected open fun onLoadError(listResult: ListResult<R>) {
        showSnackbar(SnackbarAction(message = listResult.errorMessage))
    }


    override fun loadMore() {
        loadData()
    }

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