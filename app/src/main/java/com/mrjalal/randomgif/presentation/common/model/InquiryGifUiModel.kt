package com.mrjalal.randomgif.presentation.common.model

import androidx.compose.runtime.Stable
import com.mrjalal.randomgif.domain.repository.model.util.UiModel
import kotlinx.collections.immutable.ImmutableList

@Stable
data class InquiryGifUiModel(
    val data: ImmutableList<GifUiModel>,
): UiModel
