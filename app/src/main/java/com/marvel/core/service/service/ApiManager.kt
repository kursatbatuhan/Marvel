package com.marvel.core.service.service

import com.marvel.core.utils.Constants
import com.marvel.data.characters.Character
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiManager {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String
    ): Character
}