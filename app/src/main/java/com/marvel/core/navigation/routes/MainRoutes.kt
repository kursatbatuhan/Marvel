package com.marvel.core.navigation.routes

import com.marvel.core.navigation.Routes
import javax.inject.Inject

class MainRoutes @Inject constructor() : Main {
    override fun mainFragment(): Routes {
        return Routes.NavDeepLink(
            "marvel://mainFragment"
        )
    }

    override fun secondFragment(): Routes {
        return Routes.NavDeepLink(
            "marvel://secondFragment"
        )
    }
}