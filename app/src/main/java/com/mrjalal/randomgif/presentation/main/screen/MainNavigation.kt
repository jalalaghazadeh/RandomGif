package com.mrjalal.randomgif.presentation.main.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mrjalal.randomgif.presentation.common.model.GifUiModel

const val MAIN_ROUTE = "Main"

fun NavGraphBuilder.mainScreen(onNavigateToDetailScreen: (GifUiModel) -> Unit) {
    composable(route = MAIN_ROUTE) {
        MainScreen(
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
    }
}