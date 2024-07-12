package com.marvel.core.extensions

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.marvel.core.navigation.Routes
import com.marvel.core.navigation.RoutesNavOptions

fun Fragment.navigate(
    routes: Routes,
    routesNavOptions: RoutesNavOptions? = null
) {
    val options = routesNavOptions?.getNavOptions() ?: routes.options?.getNavOptions()
    when (routes) {
        is Routes.PopBack -> {
            if (routesNavOptions != null) {
                findNavController().popBackStack(
                    routesNavOptions.popupUpTo,
                    routesNavOptions.inclusive
                )
            } else {
                findNavController().popBackStack()
            }
        }

        is Routes.NavDeepLink -> {
            val uri = Uri.parse(routes.deepLink)
            findNavController().navigate(uri, options)
        }

    }
}

fun NavController.navigateRoutes(
    routes: Routes,
    routesNavOptions: RoutesNavOptions? = null
) {
    val options = routesNavOptions?.getNavOptions() ?: routes.options?.getNavOptions()
    when (routes) {
        is Routes.PopBack -> {
            if (routesNavOptions != null) {
                this.popBackStack(
                    routesNavOptions.popupUpTo,
                    routesNavOptions.inclusive
                )
            } else {
                this.popBackStack()
            }
        }

        is Routes.NavDeepLink -> {
            val uri = Uri.parse(routes.deepLink)
            this.navigate(uri, options)
        }
    }
}
