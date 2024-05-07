package com.example.flownavigator.ui.appcontent.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.api.AuthDestination
import com.example.impl.registerAuthDestinations
import com.example.impl.registerMainDestinations


@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AuthDestination.authContentRoute,
    ) {

        registerAuthDestinations(navController)

        registerMainDestinations()
    }

}


