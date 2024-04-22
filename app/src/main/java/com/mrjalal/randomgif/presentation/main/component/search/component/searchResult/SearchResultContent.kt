package com.mrjalal.randomgif.presentation.main.component.search.component.searchResult

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.common.component.ErrorMessage
import com.mrjalal.randomgif.presentation.common.helper.getScreenWidth
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import kotlinx.collections.immutable.ImmutableList


@Composable
fun SearchResultContent(
    gifs: ImmutableList<GifUiModel>,
    onItemClick: (GifUiModel) -> Unit
) {
    val gifPreviewItemSize: Dp = (getScreenWidth() - 20.dp * 4) / 3

    if (gifs.isEmpty()) {
        ErrorMessage(
            text = stringResource(id = R.string.no_result)
        )
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(
            count = gifs.size,
            key = { gifs[it].id }
        ) {
            val item = gifs[it]
            GifPreviewItem(
                item = item,
                modifier = Modifier
                    .size(gifPreviewItemSize)
                    .padding(2.dp)
                    .clickable { onItemClick(item) },
            )
        }
    }
}