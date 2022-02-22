package com.ke.mvvm_base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.ke.mvvm.base.data.ViewStatus
import com.ke.mvvm.base.ui.collectLoadingDialog
import com.ke.mvvm.base.ui.collectSnackbarFlow
import com.ke.mvvm.base.ui.launchAndRepeatWithViewLifecycle
import com.ke.mvvm_base.R
import com.ke.mvvm_base.databinding.ActivityProfileBinding
import kotlinx.coroutines.flow.collect

class ProfileActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "个人信息"

        launchAndRepeatWithViewLifecycle {
            viewModel.viewStatus.collect {
                when (it) {
                    is ViewStatus.Content -> {
                        binding.content.isVisible = true
                        binding.retry.isVisible = false
                        binding.loading1.isVisible = false
                        binding.username.setText(it.data.username)
                        binding.username.setSelection(it.data.username.length)
                        binding.sign.setText(it.data.sign)
                        binding.sign.setSelection(it.data.sign.length)
                        binding.rgGender.setOnCheckedChangeListener(null)
                        binding.rgGender.check(if (it.data.gender) R.id.rb_man else R.id.rb_woman)
                        binding.rgGender.setOnCheckedChangeListener(this@ProfileActivity)
                        binding.level.text = it.data.level.toString()
                    }
                    is ViewStatus.Error -> {
                        binding.content.isVisible = false
                        binding.loading1.isVisible = false
                        binding.retry.isVisible = true
                    }
                    is ViewStatus.Loading -> {
                        binding.content.isVisible = false
                        binding.loading1.isVisible = true
                        binding.retry.isVisible = false

                    }

                }
            }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.navigationActon.collect {
                when (it) {
                    ProfileNavigationAction.Return -> {
                        onBackPressed()
                    }
                }
            }
        }

        collectSnackbarFlow(viewModel)
        collectLoadingDialog(viewModel)

        binding.retry.setOnClickListener {
            viewModel.loadProfile()
        }

        binding.username.addTextChangedListener {
            viewModel.updateUsername(it?.toString() ?: "")
        }
        binding.sign.addTextChangedListener {
            viewModel.updateSign(it?.toString() ?: "")
        }

        binding.titleLevel.setOnClickListener {
            val current = (viewModel.viewStatus.value as? ViewStatus.Content<Profile>)?.data?.level
                ?: return@setOnClickListener
            AlertDialog.Builder(this)
                .setTitle("选择级别")
                .setSingleChoiceItems(
                    LEVEL_LIST.map { it.toString() }.toTypedArray(),
                    LEVEL_LIST.indexOf(current)
                ) { dialog, which ->
                    dialog.dismiss()
                    viewModel.updateLevel(LEVEL_LIST[which])
                }.show()
        }

        binding.save.setOnClickListener {
            viewModel.save()
        }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        viewModel.updateGender(checkedId == R.id.rb_man)
    }


    companion object {
        private val LEVEL_LIST = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10)
    }
}