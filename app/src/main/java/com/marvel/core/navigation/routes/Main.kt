package com.marvel.core.navigation.routes

import com.marvel.core.navigation.Routes

interface Main {

    fun mainFragment(): Routes
    fun secondFragment(): Routes
    fun charactersFragment(): Routes
    fun creatorsFragment(): Routes
    fun storiesFragment(): Routes
}