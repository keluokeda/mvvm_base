package com.ke.mvvm_base.ui

import androidx.annotation.IntRange

data class Profile(
    val username: String = "路飞",
    val sign: String = "我是要成为海贼王的男人",
    /**
     * 性别 true表示男 false表示女
     */
    val gender: Boolean = true,

    @IntRange(from = 1, to = 10)
    val level: Int = 1
) {
    fun updateUsername(text: String): Profile {
        return Profile(text, sign, gender, level)
    }

    fun updateSign(text: String): Profile {
        return Profile(username, text, gender, level)
    }

    fun updateGender(value: Boolean): Profile {
        return Profile(username, sign, value, level)
    }

    fun updateLevel(value: Int): Profile {
        return Profile(username, sign, gender, value)
    }
}


