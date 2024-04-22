package com.mrjalal.randomgif.presentation.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.mrjalal.randomgif.presentation.common.model.GifUiModel
import com.mrjalal.randomgif.presentation.detail.screen.detailScreen
import com.mrjalal.randomgif.presentation.detail.screen.navigateToDetailScreen
import com.mrjalal.randomgif.presentation.main.screen.MAIN_ROUTE
import com.mrjalal.randomgif.presentation.main.screen.mainScreen


@Composable
fun RandomGifApp() {
    val navController = rememberNavController()
    NavigationGraph(navController)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    val onBack = navController::navigateUp
    val onNavigateToDetailScreen: (GifUiModel) -> Unit = { navController.navigateToDetailScreen(it.id) }

    NavHost(navController = navController, startDestination = MAIN_ROUTE) {
        mainScreen(onNavigateToDetailScreen = onNavigateToDetailScreen)
        detailScreen(onBack = onBack)
    }
}
