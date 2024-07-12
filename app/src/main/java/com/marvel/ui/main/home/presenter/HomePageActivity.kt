package com.marvel.ui.main.home.presenter

import android.annotation.SuppressLint
import androidx.navigation.fragment.NavHostFragment
import com.marvel.R
import com.marvel.core.platform.BaseActivity
import com.marvel.databinding.ActivityHomeBinding
import com.marvel.ui.main.home.domain.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity: BaseActivity<ActivityHomeBinding, HomePageViewModel>(
    layoutId = R.layout.activity_home,
    viewModelClass = HomePageViewModel::class.java
) {
    @SuppressLint("SetTextI18n")
    override fun onInitDataBinding() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activityMainContainerViewHome) as NavHostFragment
        navController = navHostFragment.navController

        binding.apply {
            activityMainChipNavigationBar.setItemSelected(R.id.navigation_characters, true)

            activityMainChipNavigationBar.setOnItemSelectedListener { item ->
                when(item) {
                    R.id.navigation_characters -> {
                        navController.navigate(R.id.charactersFragment)
                    }
                    R.id.navigation_stories -> {
                        navController.navigate(R.id.storiesFragment)
                    }
                    R.id.navigation_creators -> {
                        navController.navigate(R.id.creatorsFragment)
                    }
                }
            }
        }
    }
}