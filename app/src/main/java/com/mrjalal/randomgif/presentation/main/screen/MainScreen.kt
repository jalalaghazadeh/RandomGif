package com.mrjalal.randomgif.presentation.main.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.mrjalal.randomgif.presentation.common.component.gifDetail.GifDetail
import com.mrjalal.randomgif.presentation.common.helper.rememberLifecycleEvent
import com.mrjalal.randomgif.presentation.common.helper.use
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import com.mrjalal.randomgif.presentation.main.component.search.component.Search
import com.mrjalal.randomgif.presentation.main.viewModel.MainContract
import com.mrjalal.randomgif.presentation.main.viewModel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToDetailScreen: (GifUiModel) -> Unit
) {
    val (state, _, dispatcher) = use(viewModel)

    val startRandomGif = { dispatcher(MainContract.Event.OnStartRandomGif) }
    val stopRandomGif = { dispatcher(MainContract.Event.OnStopRandomGif) }
    val onSearchBarStatusChange: (Boolean) -> Unit = {
        dispatcher(MainContract.Event.OnSearchBarStatusChange(it))
    }

    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            startRandomGif()
        }

        if (lifecycleEvent == Lifecycle.Event.ON_PAUSE) {
            stopRandomGif()
        }
    }

    HandleBackButton(
        isSearchBarActive = state.isSearchBarActive,
        onSearchBarStatusChange = onSearchBarStatusChange
    )

    Scaffold(
        containerColor = Color.White
    ) {
        MainScreenContent(
            state = state,
            modifier = Modifier.padding(it),
            onSearchBarStatusChange = onSearchBarStatusChange,
            onSearchResultItemClick = onNavigateToDetailScreen,
            onTryAgain = startRandomGif
        )
    }
}

@Composable
fun MainScreenContent(
    state: MainContract.State,
    modifier: Modifier,
    onSearchBarStatusChange: (Boolean) -> Unit,
    onSearchResultItemClick: (GifUiModel) -> Unit,
    onTryAgain: () -> Unit
) {
    Column(modifier.padding(top = 8.dp)) {
        Search(
            active = state.isSearchBarActive,
            onSearchBarStatusChange = onSearchBarStatusChange,
            onSearchResultItemClick = onSearchResultItemClick
        )
        if (!state.isSearchBarActive) {
            GifDetail(
                item = state.randomGif,
                onTryAgain = onTryAgain
            )
        }
    }
}

@Composable
private fun HandleBackButton(
    isSearchBarActive: Boolean,
    onSearchBarStatusChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    BackHandler {
        if (isSearchBarActive) {
            onSearchBarStatusChange(false)
            return@BackHandler
        }

        (context as? Activity)?.finish()
    }
}
