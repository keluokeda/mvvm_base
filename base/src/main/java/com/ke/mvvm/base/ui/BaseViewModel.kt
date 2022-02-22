package com.ke.mvvm.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.model.SnackbarAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), IBaseViewModel {

    private val _snackbarAction = Channel<SnackbarAction>(capacity = Channel.CONFLATED)

    override val snackbarAction: Flow<SnackbarAction>
        get() = _snackbarAction.receiveAsFlow()

    private val _loadingDialogVisible = MutableStateFlow<String?>(null)

    override val loadingDialogVisible: StateFlow<String?>
        get() = _loadingDialogVisible

    protected open fun showSnackbar(snackbarAction: SnackbarAction) {
        viewModelScope.launch {
            _snackbarAction.send(snackbarAction)
        }
    }

    protected open fun showLoadingDialog(message: String) {
        _loadingDialogVisible.value = message
    }


    protected open fun dismissLoadingDialog() {
        _loadingDialogVisible.value = null
    }
}