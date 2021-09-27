package com.ke.mvvm.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule


/**
 * IBaseAdapter默认实现类
 */
abstract class BaseViewBindingAdapter<T, VB : ViewBinding> :
    BaseQuickAdapter<T, ViewBindingViewHolder<VB>>(0), IBaseAdapter<T, VB>, LoadMoreModule {


    abstract fun createViewBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VB

    override fun onCreateDefViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<VB> {


        val viewBinding = createViewBinding(LayoutInflater.from(parent.context), parent, viewType)
        return ViewBindingViewHolder(viewBinding)
    }

    abstract fun bindItem(item: T, viewBinding: VB, viewType: Int, position: Int)


    override fun convert(holder: ViewBindingViewHolder<VB>, item: T) {

        val position = getItemPosition(item)

        bindItem(item, holder.viewBinding, getDefItemViewType(position), position)
    }


    /**
     * 禁止重写此方法
     */
    final override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun setEmptyLayout(layoutId: Int) {
        setEmptyView(layoutId)
    }

    override fun loadMoreComplete() {
        loadMoreModule.loadMoreComplete()
    }

    override fun loadMoreEnd() {
        loadMoreModule.loadMoreEnd()
    }

    override fun loadMoreFail() {
        loadMoreModule.loadMoreFail()
    }

    override fun setOnLoadMoreListener(method: (Unit) -> Unit) {
        loadMoreModule.setOnLoadMoreListener {
            method.invoke(Unit)
        }
    }

    override fun setDataList(list: List<T>) {
        setList(list)
    }

    override fun asRecyclerViewAdapter(): RecyclerView.Adapter<out RecyclerView.ViewHolder> {
        return this
    }

}