package com.marvel.ui.main.characters.presenter

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import com.marvel.R
import com.marvel.core.platform.BaseFragment
import com.marvel.databinding.FragmentCharactersBinding
import com.marvel.ui.main.characters.domain.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment: BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    layoutId = R.layout.fragment_characters,
    viewModelClass = CharactersViewModel::class.java
) {
    override fun onInitDataBinding() {
        binding.apply {
            viewModel.getAllCharacters(30)
            CoroutineScope(Dispatchers.Main).launch {
                viewModel._characterData.collect { value ->
                    when {
                        value.isLoading -> {
                            println("loading")
                        }

                        value.error.isNotBlank() -> {
                            println("error")
                        }

                        value.characterList.isNotEmpty() -> {
                            fragmentCharactersCv.setContent {
                                Column {
                                    value.characterList.forEach {
                                        Text(text = it.name)
                                    }
                                }
                            }

                        }
                    }
                    delay(1000)
                }
            }
        }
    }

}