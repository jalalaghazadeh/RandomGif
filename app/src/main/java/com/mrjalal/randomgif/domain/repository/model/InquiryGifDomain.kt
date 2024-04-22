package com.mrjalal.randomgif.domain.repository.model

import androidx.compose.runtime.Stable
import com.mrjalal.randomgif.domain.repository.model.util.Domain
import kotlinx.collections.immutable.ImmutableList

@Stable
data class InquiryGifDomain(
    val data: ImmutableList<GifDomain>,
): Domain
