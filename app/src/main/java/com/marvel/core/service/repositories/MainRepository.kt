package com.marvel.core.service.repositories

import com.marvel.data.characters.Character

interface MainRepository {
    suspend fun getAllCharacters(offset: Int):Character
}