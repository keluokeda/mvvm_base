package com.ke.mvvm_base.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ke.mvvm.base.databinding.KeMvvmLayoutBaseRefreshListRetryBinding
import com.ke.mvvm.base.ui.IBaseRefreshAndLoadMoreView
import com.ke.mvvm_base.R

class MultiItemActivity : AppCompatActivity(), IBaseRefreshAndLoadMoreView {


    private val viewModel: MultiItemViewModel by viewModels()
    private val adapter = MultiItemAdapter()

    override val emptyLayoutId: Int
        get() = R.layout.layout_empty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = KeMvvmLayoutBaseRefreshListRetryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            viewModel,
            adapter,
            binding.recyclerView,
            this
        )

    }
}