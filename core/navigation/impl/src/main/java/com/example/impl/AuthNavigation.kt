package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
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

private fun NavGraphBuilder.addMainDestination() {
    composable<MainDestination> {
        MainAppContentScreen()
    }
}

private fun NavGraphBuilder.addSignInDestination(navController: NavHostController) {
    composable<AuthDestination> {
        AuthContent(navController)
    }
}