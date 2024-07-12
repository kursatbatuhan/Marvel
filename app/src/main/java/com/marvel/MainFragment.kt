package com.marvel

import android.annotation.SuppressLint
import com.marvel.core.platform.BaseFragment
import com.marvel.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    layoutId = R.layout.fragment_main,
    viewModelClass = MainFragmentViewModel::class.java
) {
    @SuppressLint("SetTextI18n")
    override fun onInitDataBinding() {
        binding.apply {
            mainFragmentTv.text = "HELLO BASE FRAGMENT"
            mainFragmentBtnRouteTest.setOnClickListener {
                viewModel.redirectToSecondFragment()
            }
        }
    }
}