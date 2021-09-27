package com.ke.mvvm_base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.databinding.KeMvvmLayoutBaseRefreshListRetryBinding
import com.ke.mvvm.base.ui.BaseViewBindingAdapter
import com.ke.mvvm.base.ui.IBaseRefreshAndLoadMoreView
import com.ke.mvvm_base.entity.Photo
import com.ke.mvvm_base.R
import com.ke.mvvm_base.databinding.ItemPhotoBinding


class GalleryFragment : Fragment(R.layout.ke_mvvm_layout_base_refresh_list_retry),
    IBaseRefreshAndLoadMoreView {

    private val adapter =
        GalleryAdapter()

    private val viewModel: GalleryViewModel by viewModels()

    private val binding: KeMvvmLayoutBaseRefreshListRetryBinding by viewbind()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        setupRefreshAndLoadMore(
            binding.swipeRefreshLayout,
            viewModel,
            adapter, binding.recyclerView,
            viewLifecycleOwner
        )
    }

    override val emptyLayoutId: Int
        get() = R.layout.layout_empty

}