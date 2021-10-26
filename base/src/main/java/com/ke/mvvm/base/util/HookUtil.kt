package com.ke.mvvm.base.util

import android.content.Context
import java.lang.Exception

class HookUtil {

    /**
     * 是否被hook了
     */
    private fun isHook(context: Context): Boolean {
        val whiteList = listOf(
            "com.android",
            "java.lang",
            "android",
            context.packageName
        )
        try {
            throw Exception("gg")
        } catch (e: Exception) {
            e.stackTrace.forEach { element ->

                if (!whiteList.map {
                        element.className.startsWith(it)
                    }.contains(true)) {
                    return true
                }
            }
        }

        return false
    }
}