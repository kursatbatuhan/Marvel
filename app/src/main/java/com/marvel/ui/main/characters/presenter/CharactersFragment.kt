package com.marvel.ui.main.characters.presenter

import com.marvel.R
import com.marvel.core.platform.BaseFragment
import com.marvel.databinding.FragmentCharactersBinding
import com.marvel.ui.main.characters.domain.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment: BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    layoutId = R.layout.fragment_characters,
    viewModelClass = CharactersViewModel::class.java
) {
    override fun onInitDataBinding() {
        binding.apply {
            fragmentCharactersTv.text = "CHARACTERS"
        }
    }
}