package com.mrjalal.randomgif.presentation.common.mapper

import com.mrjalal.randomgif.domain.repository.model.GifDomain
import com.mrjalal.randomgif.domain.repository.model.util.Mapper
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import javax.inject.Inject

class GifDomainToUiModelMapper @Inject constructor() : Mapper<GifDomain, GifUiModel> {
    override fun map(input: GifDomain): GifUiModel {
        return GifUiModel(
            gifPageUrl = input.gifPageUrl,
            url = input.url,
            previewUrl = input.previewUrl,
            id = input.id,
            rating = input.rating,
            title = input.title
        )
    }
}