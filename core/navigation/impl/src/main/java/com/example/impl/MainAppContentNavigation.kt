package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.api.MainDestination
import com.example.home.ui.MainAppContentScreen

fun NavGraphBuilder.registerMainDestinations() {

    navigation(
        route = MainDestination.baseRoute,
        startDestination = MainDestination.mainRoute
    ) {

        composable(route = MainDestination.mainRoute, deepLinks = listOf(navDeepLink {
            uriPattern = "https://www.example.com/${MainDestination.mainRoute}"
        })) {
            MainAppContentScreen()
        }
    }
}