package com.marvel.core.navigation.routes

import com.marvel.core.navigation.Routes
import com.marvel.data.cachedatamodel.CharacterCacheDataModel

interface Main {

    fun charactersFragment(): Routes
    fun creatorsFragment(): Routes
    fun storiesFragment(): Routes
    fun detailsFragment(data: CharacterCacheDataModel): Routes
}