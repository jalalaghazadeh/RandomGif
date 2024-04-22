package com.mrjalal.randomgif.presentation.common.model

import androidx.compose.runtime.Stable
import com.mrjalal.randomgif.domain.repository.model.util.UiModel

@Stable
data class SingleGifUiModel(
    val data: GifUiModel,
): UiModel