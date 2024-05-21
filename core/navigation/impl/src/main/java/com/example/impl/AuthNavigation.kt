package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.api.AuthDestination
import com.example.api.LinkPatterns
import com.example.api.MainDestination
import com.example.home.ui.AuthContent
import com.example.home.ui.MainAppContentScreen

fun NavGraphBuilder.registerAuthDestinations(
    navController: NavHostController,
) {
    addSignInDestination(navController)

    addMainDestination()
}

private fun NavGraphBuilder.addMainDestination() {
    val uri = LinkPatterns.uri
    composable<MainDestination>(deepLinks = listOf(
        navDeepLink { uriPattern = "$uri/blog" },
        navDeepLink { uriPattern = "$uri/profile/{idArg}" }
    )) {
        MainAppContentScreen()
    }
}

private fun NavGraphBuilder.addSignInDestination(navController: NavHostController) {
    composable<AuthDestination> {
        AuthContent(navController)
    }
}