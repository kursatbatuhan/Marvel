package com.marvel.ui.main.characters.domain

import androidx.lifecycle.viewModelScope
import com.marvel.core.platform.BaseViewModel
import com.marvel.core.utils.BaseResponse
import com.marvel.core.utils.Constants.StringParameter.UNEXPECTED_ERROR
import com.marvel.data.states.CharactersListState
import com.marvel.data.usecases.CharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
) : BaseViewModel() {
    private val characterData = MutableStateFlow(CharactersListState())
    val _characterData: StateFlow<CharactersListState> = characterData

    fun getAllCharacters(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        charactersUseCase(offset = offset).collect {
            when (it) {
                is BaseResponse.Success -> {
                    characterData.value =
                        CharactersListState(characterList = it.data ?: emptyList())
                }

                is BaseResponse.Loading -> {
                    characterData.value = CharactersListState(isLoading = true)
                }

                is BaseResponse.Error -> {
                    characterData.value =
                        CharactersListState(error = it.message ?: UNEXPECTED_ERROR)
                }
            }
        }
    }
}