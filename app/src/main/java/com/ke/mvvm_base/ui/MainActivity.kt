package com.ke.mvvm_base.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.ke.mvvm_base.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setOnClickListener {
            startActivity(
                Intent(
                    this, ProfileActivity::class.java
                )
            )
        }

        binding.mine.setOnClickListener {
            startActivity(
                Intent(
                    this, MineActivity::class.java
                )
            )
        }

        binding.refreshAndLoadMore.setOnClickListener {
            startActivity(
                Intent(
                    this, GalleryActivity::class.java
                )
            )
        }

        binding.refreshAndLoadMoreOptions.setOnClickListener {
            val checkedList = mutableListOf(false, false)

            AlertDialog.Builder(this)
                .setMultiChoiceItems(
                    arrayOf("下拉刷新", "上拉加载"), checkedList.toBooleanArray()
                ) { _, index, checked ->
                    checkedList[index] = checked
                }
                .setPositiveButton("跳转") { _, _ ->

                    startActivity(Intent(this, WithoutRefreshAndLoadMoreActivity::class.java)
                        .apply {
                            putExtra(
                                WithoutRefreshAndLoadMoreActivity.EXTRA_REFRESH_ENABLE,
                                checkedList[0]
                            )
                            putExtra(
                                WithoutRefreshAndLoadMoreActivity.EXTRA_LOAD_MORE_ENABLE,
                                checkedList[1]
                            )
                        }
                    )
                }.show()
        }

        binding.multiItem.setOnClickListener {
            startActivity(Intent(this, MultiItemActivity::class.java))
        }


    }
}