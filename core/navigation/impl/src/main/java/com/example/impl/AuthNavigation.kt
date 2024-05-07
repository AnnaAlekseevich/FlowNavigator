package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.api.AuthDestination
import com.example.api.MainDestination
import com.example.home.ui.AuthContent
import com.example.home.ui.MainAppContentScreen

fun NavGraphBuilder.registerAuthDestinations(
    navController: NavHostController,
) {
    addSignInDestination(navController)

    addMainDestination()
}

private fun NavGraphBuilder.addMainDestination() =
    composable(route = MainDestination.mainRoute, deepLinks = listOf(navDeepLink {
        uriPattern = "https://www.example.com/${MainDestination.mainRoute}"
    })
        ) {
        MainAppContentScreen()
    }

private fun NavGraphBuilder.addSignInDestination(navController: NavHostController) {
    composable(
        route = AuthDestination.authContentRoute,
    ) {
        AuthContent(navController)
    }
}