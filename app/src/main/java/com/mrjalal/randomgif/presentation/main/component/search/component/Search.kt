package com.mrjalal.randomgif.presentation.main.component.search.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrjalal.randomgif.presentation.common.helper.use
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import com.mrjalal.randomgif.presentation.main.component.search.component.searchResult.SearchResult
import com.mrjalal.randomgif.presentation.main.component.search.component.searchBar.SearchBar
import com.mrjalal.randomgif.presentation.main.component.search.viewModel.SearchContract
import com.mrjalal.randomgif.presentation.main.component.search.viewModel.SearchViewModel

@Composable
fun Search(
    viewModel: SearchViewModel = hiltViewModel(),
    active: Boolean,
    onSearchBarStatusChange: (Boolean) -> Unit,
    onSearchResultItemClick: (GifUiModel) -> Unit
) {
    val (state, _, dispatcher) = use(viewModel)

    SearchBar(
        active = active,
        query = state.query,
        onSearchBarStatusChange = {
            onSearchBarStatusChange(it)
            dispatcher(SearchContract.Event.ResetSearchQuery)
        },
        onQueryChanged = {
            dispatcher(SearchContract.Event.OnQueryChanged(it))
        },
        resetSearchQuery = {
            dispatcher(SearchContract.Event.ResetSearchQuery)
        }
    )
    if (active) {
        SearchResult(
            searchResult = state.searchResult,
            onTryAgain = {
                dispatcher(SearchContract.Event.OnTryAgain)
            },
            onSearchResultItemClick = onSearchResultItemClick
        )
    }
}
