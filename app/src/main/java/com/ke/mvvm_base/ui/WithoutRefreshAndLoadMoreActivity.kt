package com.ke.mvvm_base.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ke.mvvm.base.databinding.KeMvvmLayoutBaseRefreshListRetryBinding
import com.ke.mvvm.base.ui.IBaseRefreshAndLoadMoreView
import com.ke.mvvm_base.R

class WithoutRefreshAndLoadMoreActivity : AppCompatActivity(), IBaseRefreshAndLoadMoreView {

    private val viewModel: GalleryViewModel by viewModels()

    private val adapter =
        GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = KeMvvmLayoutBaseRefreshListRetryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            viewModel,
            adapter,
            binding.recyclerView,
            this,
            intent.getBooleanExtra(EXTRA_REFRESH_ENABLE, false),
            intent.getBooleanExtra(EXTRA_LOAD_MORE_ENABLE, false)
        )

    }


    companion object {
        const val EXTRA_REFRESH_ENABLE = "EXTRA_REFRESH_ENABLE"
        const val EXTRA_LOAD_MORE_ENABLE = "EXTRA_LOAD_MORE_ENABLE"
    }

    override val emptyLayoutId: Int
        get() = R.layout.layout_empty
}