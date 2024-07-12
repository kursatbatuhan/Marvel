package com.marvel.core.platform

import com.marvel.core.navigation.Routes
import com.marvel.core.navigation.RoutesNavOptions

sealed class BaseViewEvent {
    data class Navigation(val routes: Routes, val options: RoutesNavOptions? = null) :
        BaseViewEvent()
}