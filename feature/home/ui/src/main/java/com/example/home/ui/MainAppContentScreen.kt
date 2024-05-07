package com.example.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.api.BlogDestination
import com.example.api.PostsDestination
import com.example.api.ProfileDestination
import com.example.home.navigation.BlogNavHost
import com.example.home.navigation.PostsNavHost
import com.example.home.navigation.ProfileNavHost
import com.example.home.ui.bottombar.BottomNavBar
import com.example.home.ui.toolbar.DestinationTopBar
import com.example.home.ui.toolbar.TopBarViewState

@Composable
fun MainAppContentScreen(
) {
    val isDimmed = remember { mutableStateOf(false) }
    val navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination by remember {
        derivedStateOf {
            navBackStackEntry.value?.destination
        }
    }
    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
    val topBarViewState =
        remember { mutableStateOf<TopBarViewState>(TopBarViewState.NoTopBar) }

    val userUIModel = mainScreenViewModel.userModelState.collectAsState()

    Scaffold(
        topBar = {
            DestinationTopBar(
                userUiModelInfo = userUIModel.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue),
                topBarViewState = topBarViewState.value
            )

        },
        bottomBar = {
            Box {
                BottomNavBar(
                    navController = navController,
                    currentDestination = currentDestination
                )
                if (isDimmed.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray)
                    )
                }
            }
        },
        contentWindowInsets =
        WindowInsets(
            top = 0.dp,
            bottom = 0.dp,
        ),
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = BlogDestination.blogContentRoute
            ) {
                composable(BlogDestination.blogContentRoute) {
                    BlogNavHost { topBar ->
                        topBarViewState.value = topBar
                    }
                }
                composable(PostsDestination.postsRoute) {
                    PostsNavHost { topBar ->
                        topBarViewState.value = topBar
                    }
                }
                composable(ProfileDestination.profileRoute, deepLinks = listOf(navDeepLink {
                    uriPattern = "https://www.example.com/${ProfileDestination.profileRoute}"
                })) {
                    ProfileNavHost { topBar ->
                        topBarViewState.value = topBar
                    }
                }
            }
        }
    }
}