package com.ke.mvvm_base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ke.mvvm.base.data.ViewStatus
import com.ke.mvvm.base.ui.launchAndRepeatWithViewLifecycle
import com.ke.mvvm_base.R
import com.ke.mvvm_base.databinding.ActivityMineBinding
import kotlinx.coroutines.flow.collect
import java.lang.RuntimeException

class MineActivity : AppCompatActivity() {

    private val viewModel: MineViewModel by viewModels()

    private lateinit var binding: ActivityMineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadData()
        }
        launchAndRepeatWithViewLifecycle {
            viewModel.viewStatus.collect {
                when (it) {
                    is ViewStatus.Content -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        binding.info1.text = it.data.info1.toString()
                        binding.info2.text = it.data.info2.toString()
                        binding.info3.text = it.data.info3.toString()
                        binding.info4.text = it.data.info4.toString()
                    }
                    is ViewStatus.Error -> {
                        throw RuntimeException("不应该出现错误")
                    }
                    is ViewStatus.Loading -> {
                        binding.swipeRefreshLayout.isRefreshing = true
                    }
                }
            }
        }
    }
}