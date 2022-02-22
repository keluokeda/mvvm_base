package com.ke.mvvm.base.ui

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.flow.collect

interface IBaseRefreshAndLoadMoreView : IBaseView {

    /**
     * 空布局id
     */
    val emptyLayoutId: Int


    /**
     * 设置下拉刷新和滑动到底部的时候加载更多
     */
     fun <R> setupRefreshAndLoadMore(
        swipeRefreshLayout: SwipeRefreshLayout,
        viewModel: IBaseRefreshAndLoadMoreViewModel<R>,
        adapter: IBaseAdapter<R, *>,
        recyclerView: RecyclerView,
        lifecycleOwner: LifecycleOwner,
        refreshEnable: Boolean = true,
        loadMoreEnable: Boolean = true,
    ) {
        if (recyclerView.adapter != null) {
            throw RuntimeException("请不要为Recycler设置apter，不然会在加载数据的时候显示空布局")
        }

        lifecycleOwner.launchAndRepeatWithViewLifecycle {
            viewModel.dataList.collect {
                if(it != null){
                    if (recyclerView.adapter == null) {
                        recyclerView.adapter = adapter.asRecyclerViewAdapter()
                        adapter.setEmptyLayout(emptyLayoutId)
                    }
                    bindDataList(adapter, it)
                }
            }
        }


        lifecycleOwner.launchAndRepeatWithViewLifecycle {
            viewModel.isRefreshing.collect {
                if(it != null){
                    swipeRefreshLayout.isRefreshing = it
                }
            }
        }

        lifecycleOwner.launchAndRepeatWithViewLifecycle {
            viewModel.loadDataResult.collect {
                if(it != null){
                    when (it) {
                        BaseRefreshAndLoadMoreViewModel.LOAD_DATA_RESULT_SUCCESS -> {
                            adapter.loadMoreComplete()
                        }
                        BaseRefreshAndLoadMoreViewModel.LOAD_DATA_RESULT_END -> {
                            adapter.loadMoreEnd()
                        }
                        BaseRefreshAndLoadMoreViewModel.LOAD_DATA_RESULT_ERROR -> {
                            adapter.loadMoreFail()
                        }
                    }
                }
            }
        }
        swipeRefreshLayout.isEnabled = refreshEnable

        if (refreshEnable) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.refresh()
            }
        }
        if (loadMoreEnable) {
            //点击重试会回调这个方法
            adapter.setOnLoadMoreListener {
                viewModel.loadMore()
            }
        }

    }

    /**
     * 绑定数据列表到Adapter
     */
    fun <R> bindDataList(
        adapter: IBaseAdapter<R, *>,
        it: List<R>
    ) {
        adapter.setDataList(it)
    }
}