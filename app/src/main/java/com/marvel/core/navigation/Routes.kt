package com.marvel.core.navigation

sealed class Routes(val options: RoutesNavOptions? = null) {

    open class PopBack(
        options: RoutesNavOptions? = null
    ) : Routes(options)

    open class NavDeepLink(
        val deepLink: String,
        options: RoutesNavOptions? = null
    ) : Routes(options)

}