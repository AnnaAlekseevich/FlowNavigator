package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.api.AuthDestination
import com.example.home.ui.AuthContent

fun NavGraphBuilder.registerAuthDestinations(
    navController: NavHostController,
) {

    addSignInDestination(navController)

}

private fun NavGraphBuilder.addSignInDestination(navController: NavHostController) {
    composable(
        route = AuthDestination.authContentRoute,
    ) {
        AuthContent(navController)
    }
}