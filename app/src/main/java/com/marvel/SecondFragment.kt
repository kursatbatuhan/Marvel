package com.marvel

import android.annotation.SuppressLint
import com.marvel.core.platform.BaseFragment
import com.marvel.databinding.SecondFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : BaseFragment<SecondFragmentBinding, MainFragmentViewModel>(
    layoutId = R.layout.second_fragment,
    viewModelClass = MainFragmentViewModel::class.java
) {
    @SuppressLint("SetTextI18n")
    override fun onInitDataBinding() {
        binding.apply {
            secondFragmentTv.text = "SECOND FRAGMENT"
        }
    }
}