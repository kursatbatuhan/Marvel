package com.marvel.di

import com.marvel.core.service.repositories.MainRepository
import com.marvel.core.service.repositories.implementations.MainRepositoryImpl
import com.marvel.core.service.service.ApiManager
import com.marvel.core.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideApiManager(): ApiManager {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiManager::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(apiManager: ApiManager): MainRepository {
        return MainRepositoryImpl(apiManager = apiManager)
    }
}