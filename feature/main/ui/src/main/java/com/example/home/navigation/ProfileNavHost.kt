package com.example.home.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.api.BottomSheet
import com.example.api.LinkPatterns
import com.example.api.Overlay
import com.example.api.Profile
import com.example.api.ProfileSettings
import com.example.api.Test
import com.example.profile.ui.ProfileScreen
import com.example.profile.ui.ProfileSettingsScreen
import com.example.profile.ui.TestScreen
import com.example.profile.ui.contentExample.ContentExample
import com.example.uikit.R
import com.example.uikit.bottomSheet.BottomSheetScreen

@Composable
fun ProfileNavHost(profileNavController: NavHostController) {
    val overlayNavController = rememberNavController()

    val uri = LinkPatterns.uri
    Box(modifier = Modifier.fillMaxSize()) {
    // Main NavHost
    NavHost(
        navController = profileNavController,
        startDestination = Profile
    ) {
        composable<Profile>(
            deepLinks = listOf(
                navDeepLink<ProfileSettings>(basePath = "$uri/profile/{idArg}")
            )
        )
        {
            ProfileScreen(
                onNavigateToProfile = { idArg ->
                    profileNavController.navigate(ProfileSettings(idArg))
                },
                onBottomSheet = {
                    overlayNavController.navigate(BottomSheet)
                }
            )
        }

        composable<ProfileSettings> { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("idArg") ?: 0
            ProfileSettingsScreen(
                userId = id,
                onBack = profileNavController::popBackStack,
                onTestScreen = {
                    profileNavController.navigate(Test) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable<Test>(
            deepLinks = listOf(
                navDeepLink<Test>(basePath = "$uri/profile/100/test")
            )
        ) {
            TestScreen(onBack = profileNavController::popBackStack)
        }
    }

        // Overlay NavHost for Bottom Sheet
        NavHost(
            navController = overlayNavController,
            startDestination = Overlay,
            modifier = Modifier.zIndex(1f)
        ) {
            composable<Overlay> { /* Empty screen */ }
            composable<BottomSheet>(
                deepLinks = listOf(
                    navDeepLink<BottomSheet>(basePath = "$uri/profile/bottomSheet")
                )
            ) {
                BottomSheetScreen(
                    title = stringResource(R.string.bottom_sheet_string),
                    onBack = { overlayNavController.popBackStack() },
                    content = { ContentExample() }
                )
            }
        }
    }
}
