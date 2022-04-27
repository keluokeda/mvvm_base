package com.ke.mvvm.base.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.ke.mvvm.base.model.SnackbarAction
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


inline fun AppCompatActivity.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
//    lifecycleScope.launch {
//        lifecycle.repeatOnLifecycle(minActiveState) {
//            block()
//        }
//    }
    (this as LifecycleOwner).launchAndRepeatWithViewLifecycle(minActiveState, block)
}

inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {

    viewLifecycleOwner.launchAndRepeatWithViewLifecycle(minActiveState, block)
//        .lifecycleScope.launch {
//        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
//            block()
//        }
//}
}

inline fun LifecycleOwner.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

fun Fragment.collectSnackbarFlow(iBaseViewModel: IBaseViewModel) {
    (activity as? AppCompatActivity)?.collectSnackbarFlow(iBaseViewModel)
}


fun AppCompatActivity.collectSnackbarFlow(iBaseViewModel: IBaseViewModel) {
    launchAndRepeatWithViewLifecycle {
        iBaseViewModel.snackbarAction.collect {

            currentFocus?.apply {
                hideKeyboard(this)
            }
            findViewById<View>(android.R.id.content).apply {
                it.show(this)
            }
        }
    }
}

fun Fragment.collectLoadingDialog(iBaseViewModel: IBaseViewModel) {
    (activity as? AppCompatActivity)?.collectLoadingDialog(iBaseViewModel)
}

fun AppCompatActivity.collectLoadingDialog(iBaseViewModel: IBaseViewModel) {
    launchAndRepeatWithViewLifecycle {
        val progressDialog = ProgressDialog(this@collectLoadingDialog)
        progressDialog.setCancelable(false)
        iBaseViewModel.loadingDialogVisible.collect {
            if (it == null) {
                progressDialog.dismiss()
            } else {
                progressDialog.setTitle(it)
                progressDialog.show()
            }
        }
    }
}

/**
 * 隐藏软键盘
 */
fun hideKeyboard(target: View) {
    val manager =
        target.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(target.windowToken, 0)
    target.clearFocus()
}

