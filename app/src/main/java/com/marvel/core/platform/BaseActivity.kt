package com.marvel.core.platform

import android.net.Uri
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.marvel.core.extensions.navigateRoutes
import com.marvel.core.utils.SingleLiveEvent

abstract class BaseActivity<DB: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
): AppCompatActivity() {

    private val _deepLinkEvent = SingleLiveEvent<Uri>()
    val deepLinkEvent: LiveData<Uri> = _deepLinkEvent

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId) as DB
    }

    val viewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        onInitDataBinding()
    }

    fun onViewEvent(event: BaseViewEvent) {
        when (event) {

            is BaseViewEvent.Navigation -> {
                navController.navigateRoutes(
                    routes = event.routes,
                    routesNavOptions = event.options
                )
            }
        }
    }

    abstract fun onInitDataBinding()

    fun popBack(count: Int) {
        for (i in (1..count))
            navController.popBackStack()
    }

    lateinit var navController: NavController
}