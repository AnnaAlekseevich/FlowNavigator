package com.example.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.api.LinkPatterns
import com.example.api.Profile
import com.example.api.ProfileSettings
import com.example.home.ui.toolbar.TopBarViewState
import com.example.profile.ui.ProfileScreen
import com.example.profile.ui.ProfileSettingsScreen
import com.example.uikit.R

@Composable
fun ProfileNavHost(topBarStateListener: (TopBarViewState) -> Unit) {

    val profileNavController = rememberNavController()
    val showSheet = rememberSaveable { mutableStateOf(false) }
    val uri = LinkPatterns.uri

    NavHost(
        navController = profileNavController,
        startDestination = Profile
    ) {
        composable<Profile> {
            topBarStateListener.invoke(
                TopBarViewState.UserRootTopBar(
                    menuItems = listOf(
                        Pair(R.drawable.ic_arrow_down) {
                            showSheet.value = true
                        }
                    )
                )
            )
            ProfileScreen(onNavigateToProfile = { idArg ->
                profileNavController.navigate(ProfileSettings(idArg))
            })
        }

        composable<ProfileSettings>(deepLinks = listOf(
            navDeepLink { uriPattern = "$uri/profile/{idArg}" }
        ))  {backStackEntry ->
            val profileSettings: ProfileSettings = backStackEntry.toRoute()

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

            val userId = profileSettings.idArg
            ProfileSettingsScreen(userId)
        }
    }
}