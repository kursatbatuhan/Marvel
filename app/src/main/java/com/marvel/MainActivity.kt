package com.marvel

import android.annotation.SuppressLint
import androidx.navigation.fragment.NavHostFragment
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewHome) as NavHostFragment
        navController = navHostFragment.navController

        binding.apply {

        }
    }
}