package com.mrjalal.randomgif.presentation.main.component.search.component.searchResult

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mrjalal.randomgif.presentation.common.component.gifDetail.GifAnimator
import com.mrjalal.randomgif.presentation.common.model.GifUiModel

@Composable
fun GifPreviewItem(
    item: GifUiModel,
    modifier: Modifier
) {
    GifAnimator(
        url = item.previewUrl,
        modifier = Modifier.fillMaxWidth(),
        containerModifier = modifier
    )
}