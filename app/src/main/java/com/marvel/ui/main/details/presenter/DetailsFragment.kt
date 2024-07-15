package com.marvel.ui.main.details.presenter

import ComicList
import ProgressBar
import android.view.View
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marvel.R
import com.marvel.core.platform.BaseFragment
import com.marvel.core.utils.Constants.StringParameter.DOT
import com.marvel.core.utils.Constants.StringParameter.EMPTY_STRING
import com.marvel.core.utils.Constants.StringParameter.IMAGE_NOT_AVAILABLE
import com.marvel.core.utils.Constants.StringParameter.MARVEL_LOGO_URL
import com.marvel.core.utils.Constants.StringParameter.NO_INFO
import com.marvel.databinding.FragmentDetailsBinding
import com.marvel.ui.components.NoResult
import com.marvel.ui.main.details.domain.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>(
    layoutId = R.layout.fragment_details,
    viewModelClass = DetailsViewModel::class.java
) {
    override fun onInitDataBinding() {
        binding.apply {

            viewModel.getCharacterByIdValue(viewModel.characterCacheData.id)
            toolbar.setToolbarTitle(R.string.character_details)
            toolbar.setToolbarLeftIcon(R.drawable.ic_back_arrow)
            toolbar.onLeftButton = { popBack(1) }
            CoroutineScope(Dispatchers.Main).launch {
                viewModel._characterValue.collect { it ->
                    when {
                        it.isLoading -> {
                            fragmentDetailCharacterImageCv.setContent {
                                ProgressBar(isLoading = true)
                            }
                        }

                        it.error.isNotBlank() -> {
                            fragmentDetailCharacterImageCv.setContent {
                                NoResult(
                                    imageResId = R.drawable.ic_avengers,
                                    text = R.string.no_internet
                                )
                            }
                            fragmentDetailComicsTitleTv.visibility = View.GONE
                        }

                        it.characterDetail.isNotEmpty() -> {
                            val description =
                                if (it.characterDetail[0].description != EMPTY_STRING) {
                                    it.characterDetail[0].description
                                } else {
                                    NO_INFO
                                }
                            fragmentDetailTitleTv.text = it.characterDetail[0].name
                            fragmentDetailDescTv.text = description
                            fragmentDetailCharacterImageCv.setContent {
                                val imageUrl =
                                    if (it.characterDetail[0].thumbnail.contains(IMAGE_NOT_AVAILABLE)) {
                                        MARVEL_LOGO_URL
                                    } else {
                                        it.characterDetail[0].thumbnail + DOT + it.characterDetail[0].thumbnailExt
                                    }
                                Box(
                                    modifier = Modifier
                                        .size(200.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = imageUrl,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(200.dp)
                                            .border(2.dp, Color.Red, CircleShape)
                                            .clip(CircleShape)
                                    )
                                }
                            }
                            fragmentDetailComicsCv.setContent {
                                Column(
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                        .padding(horizontal = 16.dp)
                                ) {
                                    ComicList(comics = it.characterDetail[0].comics)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}