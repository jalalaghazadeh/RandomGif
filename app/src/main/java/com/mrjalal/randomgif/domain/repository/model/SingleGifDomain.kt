package com.mrjalal.randomgif.domain.repository.model

import androidx.compose.runtime.Stable
import com.mrjalal.randomgif.domain.repository.model.util.Domain

@Stable
data class SingleGifDomain(
    val data: GifDomain,
): Domain