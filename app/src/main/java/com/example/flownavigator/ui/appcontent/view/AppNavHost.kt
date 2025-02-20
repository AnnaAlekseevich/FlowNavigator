package com.example.flownavigator.ui.appcontent.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.api.MainDestination
import com.example.home.ui.MainAppContentScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainDestination
    ) {

        composable<MainDestination> {
            MainAppContentScreen()
        }

    }

}


