package com.marvel.data.responsemodels

data class CharactersResponseModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val thumbnailExt: String,
    val comics: List<String>
)