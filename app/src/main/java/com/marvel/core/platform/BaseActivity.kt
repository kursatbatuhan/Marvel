package com.marvel.core.platform

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<DB: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
): AppCompatActivity() {

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId) as DB
    }

    val viewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner= this
        onInitDataBinding()
    }

    abstract fun onInitDataBinding()
}