package com.marvel.core.service.repositories.implementations

import com.marvel.core.service.repositories.MainRepository
import com.marvel.core.service.service.ApiManager
import com.marvel.data.characters.Character
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiManager: ApiManager): MainRepository {
    override suspend fun getAllCharacters(offset: Int): Character {
        return apiManager.getAllCharacters(offset = offset.toString())
    }

    override suspend fun getCharacterById(id: String): Character {
        return apiManager.getCharacterById(id)
    }

}