package com.marvel.data.states

import com.marvel.core.utils.Constants.StringParameter.EMPTY_STRING
import com.marvel.data.responsemodels.CharactersResponseModel

data class CharactersListState(
    val isLoading: Boolean = false,
    val characterList: List<CharactersResponseModel> = emptyList(),
    val error: String = EMPTY_STRING
)