package com.example.home.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.api.PostsDestination
import com.example.home.ui.FiltersScreen
import com.example.home.ui.toolbar.TopBarViewState
import com.example.posts.ui.PostsScreen
import com.example.uikit.R

@Composable
fun PostsNavHost(topBarStateListener: (TopBarViewState) -> Unit) {

    val showBottomSheet = rememberSaveable { mutableStateOf(false) }

    val postsNavController = rememberNavController()
    NavHost(
        navController = postsNavController,
        startDestination = PostsDestination.postsRoute
    ) {
        composable(route = PostsDestination.postsRoute,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://www.example.com/${PostsDestination.postsRoute}"
            })
        ) {
            topBarStateListener.invoke(
                TopBarViewState.UserRootTopBar(
                    menuItems = listOf(
                        Pair(R.drawable.ic_filter) {
                            showBottomSheet.value = true
                        }
                    )
                )
            )
            PostsScreen()
        }

    }
    FiltersScreenContent(showBottomSheet = showBottomSheet)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersScreenContent(showBottomSheet: MutableState<Boolean>) {

    val sheetState = rememberModalBottomSheetState(true)

    if (showBottomSheet.value) {
        ModalBottomSheet(
            modifier = Modifier.wrapContentHeight(),
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
            content = {
                Box(
                    modifier = Modifier.wrapContentHeight(),
                ) {
                    FiltersScreen()
                }
            }
        )
    }
}