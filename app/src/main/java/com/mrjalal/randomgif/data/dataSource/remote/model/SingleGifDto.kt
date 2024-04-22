package com.mrjalal.randomgif.data.dataSource.remote.model

import com.mrjalal.randomgif.domain.repository.model.util.Dto
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain

data class SingleGifDto(
    val data: GifDto,
): Dto {
    override fun asUiModel(): SingleGifDomain {
        return SingleGifDomain(
            data = data.asUiModel()
        )
    }
}