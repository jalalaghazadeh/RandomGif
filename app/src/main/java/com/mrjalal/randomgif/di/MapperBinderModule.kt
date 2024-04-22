package com.mrjalal.randomgif.di

import com.mrjalal.randomgif.domain.repository.model.GifDomain
import com.mrjalal.randomgif.domain.repository.model.InquiryGifDomain
import com.mrjalal.randomgif.domain.repository.model.SingleGifDomain
import com.mrjalal.randomgif.domain.repository.model.util.Mapper
import com.mrjalal.randomgif.presentation.common.mapper.GifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.mapper.InquiryGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.mapper.SingleGifDomainToUiModelMapper
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import com.mrjalal.randomgif.presentation.common.model.InquiryGifUiModel
import com.mrjalal.randomgif.presentation.common.model.SingleGifUiModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperBinderModule {
    @Binds
    abstract fun bindSingleGifMapper(singleGifDomainToUiModelMapper: SingleGifDomainToUiModelMapper): Mapper<SingleGifDomain, SingleGifUiModel>

    @Binds
    abstract fun bindInquiryGifMapper(inquiryGifDomainToUiModelMapper: InquiryGifDomainToUiModelMapper): Mapper<InquiryGifDomain, InquiryGifUiModel>

    @Binds
    abstract fun bindGifMapper(gifDomainToUiModelMapper: GifDomainToUiModelMapper): Mapper<GifDomain, GifUiModel>
}