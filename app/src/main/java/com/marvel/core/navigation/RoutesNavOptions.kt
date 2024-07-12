package com.marvel.core.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavOptions

data class RoutesNavOptions(
    @IdRes val popupUpTo: Int = -1,
    val inclusive: Boolean = false,
    val animate: Boolean = false
) {
    fun getNavOptions() = if (animate) NavOptions.Builder()
        .setEnterAnim(android.R.anim.fade_in)
        .setExitAnim(android.R.anim.fade_out)
        .setPopEnterAnim(android.R.anim.fade_in)
        .setPopExitAnim(android.R.anim.fade_out)
        .setPopUpTo(popupUpTo, inclusive).build()
    else NavOptions.Builder().setPopUpTo(popupUpTo, inclusive).build()
}