package com.marvel.core.platform

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.marvel.core.extensions.observe
import com.marvel.core.extensions.observeEvent
import com.marvel.core.utils.Constants.DeepLinkKeys.FAIL
import com.marvel.core.utils.Constants.DeepLinkKeys.SUCCESS
import com.marvel.core.utils.Constants.DeepLinkKeys.TRUSTLY
import com.marvel.core.utils.Constants.DeepLinkKeys.key
import com.marvel.core.utils.Constants.DeepLinkKeys.successOrFail
import com.marvel.ui.main.home.presenter.HomePageActivity

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : Fragment() {

    protected var activityContext: Activity? = null

    private var _binding: DB? = null
    protected val binding: DB get() = _binding!!

    lateinit var viewModelProvider: ViewModelProvider
    lateinit var pageTitle: String

    val viewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }
    var navController: NavController? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            activityContext = context
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun onInitDataBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentLifeCycle = viewLifecycleOwner
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    private fun onDeepLinkEvent(event: Uri) {
        if (activity is HomePageActivity) {
            val key = event.getQueryParameter(key)
            val successOrFail = event.getQueryParameter(successOrFail)
            if (key != null) {
                if (successOrFail != null) {
                    when (key) {
                        TRUSTLY -> {
                            when (successOrFail) {
                                SUCCESS -> {}
                                FAIL -> {}
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.lifecycleOwner = viewLifecycleOwner

        try {
            navController = (activity as BaseActivity<*, *>).navController
        } catch (e: Exception) {

            if (activity is HomePageActivity) {
                navController = (activity as HomePageActivity).navController
            }
        }
        onInitDataBinding()
        observeEvent(viewModel.baseEvent) {
            (requireActivity() as? BaseActivity<*, *>)?.onViewEvent(it)
        }
        observe((activity as BaseActivity<*, *>).deepLinkEvent, ::onDeepLinkEvent)
    }

    companion object {
        lateinit var currentLifeCycle: LifecycleOwner
    }
}