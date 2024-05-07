package com.example.home.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.api.ProfileDestination
import com.example.api.ProfileDestination.idArg
import com.example.home.ui.toolbar.TopBarViewState
import com.example.profile.ui.ProfileScreen
import com.example.profile.ui.ProfileSettingsScreen
import com.example.uikit.R

@Composable
fun ProfileNavHost(topBarStateListener: (TopBarViewState) -> Unit) {

    val profileNavController = rememberNavController()
    val showSheet = rememberSaveable { mutableStateOf(false) }

    NavHost(
        navController = profileNavController,
        startDestination = ProfileDestination.profileRoute
    ) {
        composable(route = ProfileDestination.profileRoute) {
            topBarStateListener.invoke(
                TopBarViewState.UserRootTopBar(
                    menuItems = listOf(
                        Pair(R.drawable.ic_filter) {
                            showSheet.value = true
                        }
                    )
                )
            )
            ProfileScreen(profileNavController)
        }

        composable(
            route = ProfileDestination.profileSettingsRoute,
            arguments = listOf(navArgument(idArg) { type = NavType.IntType })
        ) {
            Log.d("CheckToolbar", "route = ${profileNavController.currentDestination?.route}")
            topBarStateListener.invoke(
                TopBarViewState.ChildTopBar(
                    title = stringResource(id = R.string.profile_settings),
                    homeImageResId = R.drawable.ic_back,
                    homeActionClick = {
                        profileNavController.popBackStack()
                    },
                    menuItems = null
                )
            )

            val userId = it.arguments?.getInt(idArg) ?: 0
            ProfileSettingsScreen(userId)
        }
    }
}