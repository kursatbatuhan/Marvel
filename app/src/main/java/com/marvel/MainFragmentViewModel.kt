package com.marvel

import com.marvel.core.navigation.routes.MainRoutes
import com.marvel.core.platform.BaseViewEvent
import com.marvel.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val mainRoutes: MainRoutes
) : BaseViewModel() {

    fun redirectToSecondFragment() {
        sendEvent(
            BaseViewEvent.Navigation(
                routes = mainRoutes.secondFragment()
            )
        )
    }
}