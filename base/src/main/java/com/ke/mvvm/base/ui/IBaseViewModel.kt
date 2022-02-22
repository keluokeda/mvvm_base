package com.ke.mvvm.base.ui

import com.ke.mvvm.base.model.SnackbarAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IBaseViewModel {



    val snackbarAction: Flow<SnackbarAction>

    val loadingDialogVisible:StateFlow<String?>
}