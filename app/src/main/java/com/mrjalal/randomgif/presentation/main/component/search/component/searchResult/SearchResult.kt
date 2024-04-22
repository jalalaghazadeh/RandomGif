package com.mrjalal.randomgif.presentation.main.component.search.component.searchResult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray4
import com.mrjalal.randomgif.presentation.common.component.ErrorMessage
import com.mrjalal.randomgif.presentation.common.component.Loading
import com.mrjalal.randomgif.presentation.common.component.TryAgain
import com.mrjalal.randomgif.presentation.common.model.DataState
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import com.mrjalal.randomgif.presentation.common.model.InquiryGifUiModel

@Composable
fun SearchResult(
    searchResult: DataState<InquiryGifUiModel>,
    onTryAgain: () -> Unit,
    onSearchResultItemClick: (GifUiModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.search_result),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = Gray4,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (searchResult) {
            is DataState.Success -> {
                SearchResultContent(
                    gifs = searchResult.data.data,
                    onItemClick = onSearchResultItemClick
                )
            }

            is DataState.Error -> {
                TryAgain(
                    errorType = searchResult.errorMessage,
                    onTryAgain = onTryAgain
                )
            }

            is DataState.Loading -> {
                Loading()
            }

            is DataState.Empty -> {
                ErrorMessage(
                    text = stringResource(id = R.string.empty_result)
                )
            }
        }
    }
}


