package com.marvel.data.states

import com.marvel.core.utils.Constants.StringParameter.EMPTY_STRING
import com.marvel.data.responsemodels.CharactersResponseModel

data class CharacterDetailsState(
    val isLoading: Boolean = false,
    val characterDetail: List<CharactersResponseModel> = emptyList(),
    val error: String = EMPTY_STRING
)
