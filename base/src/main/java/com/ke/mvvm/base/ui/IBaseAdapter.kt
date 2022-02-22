package com.ke.mvvm.base.ui

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface IBaseAdapter<T, VB : ViewBinding> {

    /**
     * 加载更多完成
     */
    fun loadMoreComplete()

    /**
     * 加载更多结束，没有更多数据
     */
    fun loadMoreEnd()

    /**
     * 加载更多失败
     */
    fun loadMoreFail()

    /**
     * 设置数据
     */
    fun setDataList(list: List<T>)

    /**
     * 设置空布局
     */
    fun setEmptyLayout(@LayoutRes layoutId: Int)

    /**
     * 加载更多的回调
     */
    fun setOnLoadMoreListener(
        method: () -> Unit
    )

    /**
     * 多布局的话重写这个方法
     */
    fun getViewType(position: Int): Int = 0

    /**
     * 转化为RecyclerView的Adapter
     */
    fun asRecyclerViewAdapter(): RecyclerView.Adapter<out RecyclerView.ViewHolder>


}