package com.mrjalal.randomgif.domain.repository.model

import androidx.compose.runtime.Stable
import com.mrjalal.randomgif.domain.repository.model.util.Domain

@Stable
data class GifDomain(
    val gifPageUrl: String,
    val url: String?,
    val previewUrl: String?,
    val id: String,
    val rating: String,
    val title: String,
) : Domain
