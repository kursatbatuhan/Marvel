package com.marvel.ui.main.characters.presenter

import ProgressBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.marvel.R
import com.marvel.core.platform.BaseFragment
import com.marvel.core.utils.Constants.StringParameter.DOT
import com.marvel.core.utils.Constants.StringParameter.EMPTY_STRING
import com.marvel.core.utils.Constants.StringParameter.IMAGE_NOT_AVAILABLE
import com.marvel.core.utils.Constants.StringParameter.MARVEL_LOGO_URL
import com.marvel.core.utils.Constants.StringParameter.NO_INFO
import com.marvel.data.cachedatamodel.CharacterCacheDataModel
import com.marvel.databinding.FragmentCharactersBinding
import com.marvel.ui.components.CharacterList
import com.marvel.ui.components.NoResult
import com.marvel.ui.main.characters.domain.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    layoutId = R.layout.fragment_characters,
    viewModelClass = CharactersViewModel::class.java
) {
    private var offset = 0
    private val limit = 30

    override fun onInitDataBinding() {
        binding.apply {
            toolbar.setToolbarTitle(R.string.menu_characters)
            viewModel.getAllCharacters(offset = offset)
            CoroutineScope(Dispatchers.Main).launch {
                viewModel._characterData.collect { value ->
                    when {
                        value.isLoading -> {
                            fragmentCharactersCv.setContent {
                                ProgressBar(isLoading = true)
                            }

                        }

                        value.error.isNotBlank() -> {
                            fragmentCharactersCv.setContent {
                                NoResult(
                                    imageResId = R.drawable.ic_avengers,
                                    text = R.string.no_internet
                                )
                            }
                        }

                        value.characterList.isNotEmpty() -> {
                            fragmentCharactersCv.setContent {
                                val scrollState = rememberScrollState()
                                LaunchedEffect(value.characterList) {
                                    scrollState.scrollTo(0)
                                }
                                LaunchedEffect(scrollState.value) {
                                    if (scrollState.value == scrollState.maxValue) {
                                        offset += limit
                                        viewModel.getAllCharacters(offset = offset)
                                    }
                                }
                                Column(modifier = Modifier.verticalScroll(scrollState)) {
                                    value.characterList.forEach {
                                        val imageUrl =
                                            if (it.thumbnail.contains(IMAGE_NOT_AVAILABLE)) {
                                                MARVEL_LOGO_URL
                                            } else {
                                                "${it.thumbnail}${DOT}${it.thumbnailExt}"
                                            }
                                        val description = if (it.description != EMPTY_STRING) {
                                            it.description
                                        } else {
                                            NO_INFO
                                        }
                                        it.id
                                        CharacterList(
                                            imageUrl = imageUrl,
                                            name = it.name,
                                            description = description,
                                            onClick = {
                                                viewModel.redirectToDetailsFragment(
                                                    CharacterCacheDataModel(
                                                        id = it.id.toString()
                                                    )
                                                )
                                            }
                                        )

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
