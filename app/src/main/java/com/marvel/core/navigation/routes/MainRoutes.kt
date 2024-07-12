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

    override fun charactersFragment(): Routes {
        return Routes.NavDeepLink(
            "marvel://charactersFragment"
        )
    }

    override fun creatorsFragment(): Routes {
        return Routes.NavDeepLink(
            "marvel://creatorsFragment"
        )
    }

    override fun storiesFragment(): Routes {
        return Routes.NavDeepLink(
            "marvel://storiesFragment"
        )
    }
}