package com.ke.mvvm_base.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ke.mvvm.base.ui.BaseFragmentActivity

class GalleryActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = GalleryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "图片列表"
    }
}