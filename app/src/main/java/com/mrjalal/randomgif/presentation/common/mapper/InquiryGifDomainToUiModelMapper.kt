package com.mrjalal.randomgif.presentation.common.mapper

import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import com.mrjalal.randomgif.domain.repository.model.util.Mapper
import com.mrjalal.randomgif.presentation.common.model.InquiryGifUiModel
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class InquiryGifDomainToUiModelMapper @Inject constructor(
    private val gifDomainToUiModelMapper: GifDomainToUiModelMapper
) : Mapper<InquiryGifDomain, InquiryGifUiModel> {
    override fun map(input: InquiryGifDomain): InquiryGifUiModel {
        return InquiryGifUiModel(
            data = input.data.map {
                gifDomainToUiModelMapper.map(it)
            }.toImmutableList()
        )
    }
}