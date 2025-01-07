package com.example.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.api.BottomSheet
import com.example.api.LinkPatterns
import com.example.api.Overlay
import com.example.api.Profile
import com.example.api.ProfileSettings
import com.example.profile.ui.ProfileScreen
import com.example.profile.ui.ProfileSettingsScreen
import com.example.profile.ui.contentExample.ContentExample
import com.example.uikit.R
import com.example.uikit.bottomSheet.BottomSheetScreen

@Composable
fun ProfileNavHost() {
    val profileNavController = rememberNavController()
    val overlayNavController = rememberNavController()

    val uri = LinkPatterns.uri

    // Main NavHost
    NavHost(
        navController = profileNavController,
        startDestination = Profile
    ) {
        composable<Profile> {
            ProfileScreen(
                onNavigateToProfile = { idArg ->
                    profileNavController.navigate(ProfileSettings(idArg))
                },
                onBottomSheet = {
                    overlayNavController.navigate(BottomSheet)
                }
            )
        }

        composable<ProfileSettings>(deepLinks = listOf(
            navDeepLink { uriPattern = "$uri/profile/{idArg}" }
        )) { backStackEntry ->
            val profileSettings: ProfileSettings = backStackEntry.toRoute()

            val userId = profileSettings.idArg
            ProfileSettingsScreen(userId, onBack = profileNavController::popBackStack)
        }
    }

    // Overlay NavHost for Bottom Sheet
    NavHost(
        navController = overlayNavController,
        startDestination = Overlay,
        modifier = Modifier.zIndex(1f)
    ) {
        composable<Overlay> { /* Empty screen */ }
        composable<BottomSheet> {
            BottomSheetScreen(
                title = stringResource(R.string.bottom_sheet_string),
                onBack = { overlayNavController.popBackStack() },
                content = { ContentExample() }
            )
        }
    }
}