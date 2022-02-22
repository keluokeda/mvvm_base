package com.ke.mvvm_base.ui

import androidx.lifecycle.viewModelScope
import com.ke.mvvm.base.data.ViewStatus
import com.ke.mvvm.base.model.SnackbarAction
import com.ke.mvvm.base.ui.BaseContentViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel : BaseContentViewModel<Profile>() {

    private var showRetry = true

    private var showErrorWhenSave = true

    private val _navigationAction = Channel<ProfileNavigationAction>(capacity = Channel.CONFLATED)


    val navigationActon: Flow<ProfileNavigationAction> = _navigationAction.receiveAsFlow()


    init {
        loadProfile()
    }

    fun loadProfile() {
        showLoading()
        viewModelScope.launch {
            delay(2000)
            if (showRetry) {
                showError(true)
                showRetry = false
            } else {
                showContent(Profile())
            }

        }
    }

    fun updateUsername(text: String) {
        val profile = (viewStatus.value as? ViewStatus.Content)?.data ?: return

        showContent(profile.updateUsername(text))
    }

    fun updateSign(text: String) {
        val profile = (viewStatus.value as? ViewStatus.Content)?.data ?: return

        showContent(profile.updateSign(text))
    }

    fun updateGender(gender: Boolean) {
        val profile = (viewStatus.value as? ViewStatus.Content)?.data ?: return

        showContent(profile.updateGender(gender))
    }

    fun updateLevel(level: Int) {
        val profile = (viewStatus.value as? ViewStatus.Content)?.data ?: return

        showContent(profile.updateLevel(level))
    }

    fun save() {
        viewModelScope.launch {
            showLoadingDialog("提交中")
            delay(2000)
            dismissLoadingDialog()
            if (showErrorWhenSave) {
                showSnackbar(SnackbarAction(message = "保存失败", actionName = "重试") {
                    save()
                })
                showErrorWhenSave = false
            } else {
                _navigationAction.send(ProfileNavigationAction.Return)
            }
        }
    }
}

sealed interface ProfileNavigationAction {
    object Return : ProfileNavigationAction
}