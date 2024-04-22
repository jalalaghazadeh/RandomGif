package com.mrjalal.randomgif.data.dataSource.remote.model

import com.mrjalal.randomgif.domain.repository.model.util.Dto
import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import kotlinx.collections.immutable.toImmutableList

data class InquiryGifDto(
    val data: List<GifDto>,
): Dto {
    override fun asUiModel(): InquiryGifDomain {
        return InquiryGifDomain(
            data = data.map { it.asUiModel() }.toImmutableList()
        )
    }
}