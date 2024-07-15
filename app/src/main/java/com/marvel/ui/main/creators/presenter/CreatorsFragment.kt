package com.marvel.ui.main.creators.presenter

import com.marvel.R
import com.marvel.core.platform.BaseFragment
import com.marvel.databinding.FragmentCreatorsBinding
import com.marvel.ui.components.NoResult
import com.marvel.ui.main.creators.domain.CreatorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatorsFragment: BaseFragment<FragmentCreatorsBinding, CreatorsViewModel>(
    layoutId = R.layout.fragment_creators,
    viewModelClass = CreatorsViewModel::class.java
) {
    override fun onInitDataBinding() {
        binding.apply {
            fragmentCreatorsCv.setContent {
                NoResult(imageResId = R.drawable.ic_avengers, text = R.string.coming_soon)
            }
        }
    }
}