package com.ke.mvvm_base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val flowDemoUseCase = FlowDemoUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.content)


        lifecycleScope.launch {
            flowDemoUseCase(1)
                .collect {
                    textView.text = it.toString()
                }
        }
    }
}