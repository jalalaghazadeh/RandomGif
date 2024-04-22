package com.mrjalal.randomgif.presentation.common.mapper

import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain
import com.mrjalal.randomgif.domain.repository.model.util.Mapper
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel
import javax.inject.Inject

class SingleGifDomainToUiModelMapper @Inject constructor(
    private val gifDomainToUiModelMapper: GifDomainToUiModelMapper
): Mapper<SingleGifDomain, SingleGifUiModel> {
    override fun map(input: SingleGifDomain): SingleGifUiModel {
        return SingleGifUiModel(
            data = gifDomainToUiModelMapper.map(input.data)
        )
    }
}