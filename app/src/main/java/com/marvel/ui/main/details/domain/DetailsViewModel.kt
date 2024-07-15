package com.marvel.ui.main.details.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.marvel.core.platform.BaseViewModel
import com.marvel.core.utils.BaseResponse
import com.marvel.core.utils.Constants.StringParameter.UNEXPECTED_ERROR
import com.marvel.data.cachedatamodel.CharacterCacheDataModel
import com.marvel.data.states.CharacterDetailsState
import com.marvel.data.usecases.CharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val characterValue = MutableStateFlow(CharacterDetailsState())
    val _characterValue: StateFlow<CharacterDetailsState> = characterValue
    val characterCacheData: CharacterCacheDataModel = savedStateHandle.get<String>("data").let {
        Gson().fromJson(it, CharacterCacheDataModel::class.java)
    }

    fun getCharacterByIdValue(id: String) = viewModelScope.launch {
        characterDetailUseCase(id).collect {
            when (it) {
                is BaseResponse.Success -> {
                    characterValue.value = CharacterDetailsState(
                        characterDetail = it.data ?: emptyList()
                    )
                }

                is BaseResponse.Loading -> {
                    characterValue.value = CharacterDetailsState(isLoading = true)
                }

                is BaseResponse.Error -> {
                    characterValue.value =
                        CharacterDetailsState(error = it.message ?: UNEXPECTED_ERROR)
                }
            }
        }
    }
}