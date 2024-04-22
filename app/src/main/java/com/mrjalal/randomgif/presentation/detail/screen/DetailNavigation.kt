package com.mrjalal.randomgif.presentation.detail.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val DETAIL_ROUTE = "Detail?id={id}"

fun NavController.navigateToDetailScreen(id: String) =
    navigate("Detail?id=$id")

fun NavGraphBuilder.detailScreen(
    onBack: () -> Boolean,
) {
    composable(
        route = DETAIL_ROUTE,
        arguments = listOf(
            navArgument(name = "id") {
                type = NavType.StringType
            },
        )
    ) {
        DetailScreen(
            onBack = onBack,
            id = it.arguments?.getString("id"),
        )
    }
}