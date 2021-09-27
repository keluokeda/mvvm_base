package com.ke.mvvm.base.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar

interface IBaseView {

    fun setupRetryAndLoading(
        retryView: View,
        loadingView: View,
        contentView: View,
        baseViewModel: BaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        baseViewModel.loadingViewVisible.observe(viewLifecycleOwner) {
            loadingView.isVisible = it
        }
        baseViewModel.retryViewVisible.observe(viewLifecycleOwner) {
            retryView.isVisible = it
        }
        baseViewModel.contentViewVisible.observe(viewLifecycleOwner) {
            contentView.isVisible = it
        }
        retryView.setOnClickListener {
            baseViewModel.retry()
        }
    }

    fun setupRetry(
        retryView: View,
        contentView: View,
        viewModel: IBaseViewModel,
        viewLifecycleOwner: LifecycleOwner
    ) {
        retryView.setOnClickListener {
            viewModel.retry()
        }
        viewModel.retryViewVisible.observe(viewLifecycleOwner) {
            retryView.isVisible = it
            contentView.isVisible = !it
        }
    }


    /**
     * 设置Snackbar
     */
    fun setupSnackbar(
        viewModel: IBaseViewModel,
        viewLifecycleOwner: LifecycleOwner,
        view: View
    ) {
        viewModel.snackbarEvent.observe(viewLifecycleOwner) { action ->
            view.apply {
                //显示之前先隐藏软键盘，不然显示出来看不见
                hideKeyboard(view)
                Snackbar.make(this, action.message, action.duration).apply {
                    if (action.action != null && action.actionName != null) {
                        setAction(action.actionName) {
                            action.action.invoke()
                        }
                    }
                }.show()
            }
        }
    }

    /**
     * 隐藏软键盘
     */
    fun hideKeyboard(view: View) {
        (view.context as? Activity)?.apply {
            val target = currentFocus
            if (target != null) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken, 0)
                view.clearFocus()
            }
        }

    }
}