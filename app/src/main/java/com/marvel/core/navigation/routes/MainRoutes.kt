package com.marvel.core.navigation.routes

import com.google.gson.Gson
import com.marvel.core.navigation.Routes
import com.marvel.data.cachedatamodel.CharacterCacheDataModel
import javax.inject.Inject

class MainRoutes @Inject constructor() : Main {

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

    override fun detailsFragment(data: CharacterCacheDataModel): Routes {
        return Routes.NavDeepLink(
            "marvel://detailsFragment?data=${
                Gson().toJson(data)
            }"
        )
    }
}