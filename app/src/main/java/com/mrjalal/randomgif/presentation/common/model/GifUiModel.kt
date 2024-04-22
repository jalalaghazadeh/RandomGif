package com.mrjalal.randomgif.presentation.common.model

import androidx.compose.runtime.Stable
import com.mrjalal.randomgif.domain.repository.model.util.UiModel

@Stable
data class GifUiModel(
    val gifPageUrl: String,
    val url: String?,
    val previewUrl: String?,
    val id: String,
    val rating: String,
    val title: String,
) : UiModel
