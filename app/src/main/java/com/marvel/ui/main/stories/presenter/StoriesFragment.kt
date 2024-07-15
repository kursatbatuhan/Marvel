package com.marvel.ui.main.stories.presenter

import com.marvel.R
import com.marvel.core.platform.BaseFragment
import com.marvel.databinding.FragmentStoriesBinding
import com.marvel.ui.components.NoResult
import com.marvel.ui.main.stories.domain.StoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoriesFragment: BaseFragment<FragmentStoriesBinding, StoriesViewModel>(
    layoutId = R.layout.fragment_stories,
    viewModelClass = StoriesViewModel::class.java
) {
    override fun onInitDataBinding() {
       binding.apply {
           fragmentStoriesCv.setContent {
               NoResult(imageResId = R.drawable.ic_avengers, text = R.string.coming_soon)
           }
       }
    }
}