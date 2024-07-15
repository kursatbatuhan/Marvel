package com.marvel.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.marvel.R
import com.marvel.core.binding.visible
import com.marvel.core.service.base.app.ApplicationDI
import com.marvel.databinding.ViewToolbarBinding

class Toolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    var onLeftButton: (() -> Unit)? = null
    var onRightButton: (() -> Unit)? = null

    var leftIcon: Drawable? = null
    var title: String? = null
    var rightIcon: Drawable? = null
    var backgroundColorType: ToolType? = null
    var isBackButtonOverride: Boolean = false
    var binding: ViewToolbarBinding

    init {
        binding = ViewToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        if (attributeSet != null) {
            val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.Toolbar)
            try {

                if (attributes.hasValue(R.styleable.Toolbar_left_icon)) {
                    leftIcon = ContextCompat.getDrawable(
                        context,
                        attributes.getResourceId(R.styleable.Toolbar_left_icon, 0)
                    )
                }

                if (attributes.hasValue(R.styleable.Toolbar_title_text)) {
                    val titleResourceId =
                        attributes.getResourceId(R.styleable.Toolbar_title_text, 0)
                    title = if (titleResourceId != 0 && !isInEditMode) {
                        ApplicationDI.getAppInstance().getString(titleResourceId)
                    } else
                        attributes.getString(R.styleable.Toolbar_title_text)
                }

                if (attributes.hasValue(R.styleable.Toolbar_right_icon)) {
                    rightIcon = ContextCompat.getDrawable(
                        context,
                        attributes.getResourceId(R.styleable.Toolbar_right_icon, 0)
                    )
                }

                if (attributes.hasValue(R.styleable.Toolbar_toolbar_type)) {
                    val type = attributes.getInt(R.styleable.Toolbar_toolbar_type, 0)
                    backgroundColorType = if (type == 0) {
                        ToolType.TRANSPARENT
                    } else {
                        ToolType.GRADIENT
                    }
                }
                initView(leftIcon, title, rightIcon, backgroundColorType)
            } finally {
                attributes.recycle()
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initView(
        leftIcon: Drawable?,
        title: String?,
        rigthIcon: Drawable?,
        toolType: ToolType?
    ) {
        binding.apply {
            leftButton.visibility = View.GONE
            titleTextView.visibility = View.GONE
            rightButton.visibility = View.GONE

            leftIcon?.let {
                leftButton.visibility = View.VISIBLE
                leftButton.setImageDrawable(it)
                viewToolbarLeftButtonParent.setOnClickListener {
                    if (isBackButtonOverride) {
                        onLeftButton?.invoke()
                    } else {
                        findNavController().navigateUp()
                    }
                }
            }
            rigthIcon?.let {
                rightButton.visibility = View.VISIBLE
                rightButton.setImageDrawable(it)
                viewToolbarRightButtonParent.setOnClickListener { onRightButton?.invoke() }
            }
            binding.apply {
                if (toolType == ToolType.GRADIENT) {
                    titleTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
                    leftButton.setColorFilter(
                        ContextCompat.getColor(context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    rightButton.setColorFilter(
                        ContextCompat.getColor(context, R.color.white),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                } else {
                    leftButton.setColorFilter(
                        ContextCompat.getColor(context, R.color.marvelRed),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    rightButton.setColorFilter(
                        ContextCompat.getColor(context, R.color.marvelRed),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    titleTextView.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.marvelRed
                        )
                    )
                }
            }
            title?.let {
                titleTextView.visibility = View.VISIBLE
                titleTextView.text = it
            }
        }
    }

    fun setBackButtonOverride(isBackOverride: Boolean?) {
        if (isBackOverride != null) {
            isBackButtonOverride = isBackOverride
        }
    }

    fun setToolbarLeftButtonVisibility(visible: Boolean) {
        binding.leftButton.visibility = if (visible) View.VISIBLE else View.GONE
        binding.viewToolbarLeftButtonParent.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setToolbarRightButtonVisibility(visible: Boolean) {
        binding.rightButton.visibility = if (visible) View.VISIBLE else View.GONE
        binding.viewToolbarRightButtonParent.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @SuppressLint("ResourceAsColor")
    fun setToolbarType(toolbarType: ToolType) {
        binding.apply {
            if (toolbarType == ToolType.GRADIENT) {
                titleTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
                leftButton.setColorFilter(
                    ContextCompat.getColor(context, R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                rightButton.setColorFilter(
                    ContextCompat.getColor(context, R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            } else {
                leftButton.setColorFilter(
                    ContextCompat.getColor(context, R.color.marvelRed),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                rightButton.setColorFilter(
                    ContextCompat.getColor(context, R.color.marvelRed),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
                titleTextView.setTextColor(ContextCompat.getColor(context, R.color.marvelRed))
            }
        }
    }

    fun setToolbarTitle(@StringRes title: Int) {
        this.title = context.getString(title)
        binding.titleTextView.text = context.getString(title)
        binding.titleTextView.visibility = View.VISIBLE
    }

    fun setToolbarTitle(title: String) {
        this.title = title
        binding.titleTextView.text = title
        binding.titleTextView.visibility = View.VISIBLE
    }

    fun setToolbarLeftIcon(@DrawableRes icon: Int) {
        binding.leftButton.visibility = if (visible) View.VISIBLE else View.GONE
        Glide.with(context).load(icon).into(binding.leftButton)
        binding.viewToolbarLeftButtonParent.setOnClickListener {
            onLeftButton?.invoke()
        }
    }

    fun setToolbarRightIcon(@DrawableRes icon: Int) {
        binding.rightButton.visibility = if (visible) View.VISIBLE else View.GONE
        Glide.with(context).load(icon).into(binding.rightButton)
        binding.viewToolbarRightButtonParent.setOnClickListener {
            onRightButton?.invoke()
        }
    }

    fun setToolbarRightIconVisibility(isVisible: Boolean) =
        run { binding.rightButton.isVisible = isVisible }


}

enum class ToolType {
    TRANSPARENT,
    GRADIENT
}
