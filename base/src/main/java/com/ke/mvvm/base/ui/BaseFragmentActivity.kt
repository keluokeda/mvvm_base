package com.ke.mvvm.base.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ke.mvvm.base.R

abstract class BaseFragmentActivity : AppCompatActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ke_mvvm_layout_fragment_container)



        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
                .commit()
        }

    }

    abstract val fragment: Fragment
}