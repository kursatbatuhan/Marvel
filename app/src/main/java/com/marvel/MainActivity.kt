package com.marvel

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marvel.core.platform.BaseActivity
import com.marvel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding, MainActivityViewModel>(
    layoutId = R.layout.activity_main,
    viewModelClass = MainActivityViewModel::class.java
) {
    @SuppressLint("SetTextI18n")
    override fun onInitDataBinding() {
        binding.apply {
            activityMainHelloTv.text = "HELLO BASE ACTIVITY"
        }
    }
}