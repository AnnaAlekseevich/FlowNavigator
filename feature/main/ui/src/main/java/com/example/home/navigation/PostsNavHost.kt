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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.api.PostDetails
import com.example.api.Posts
import com.example.api.SearchParameters
import com.example.api.SearchParametersType
import com.example.home.ui.FiltersScreen
import com.example.posts.ui.PostDetailsScreen
import com.example.posts.ui.PostsScreen
import kotlin.reflect.typeOf

@Composable
fun PostsNavHost(postsNavController: NavHostController) {
    val showBottomSheet = rememberSaveable { mutableStateOf(false) }

    NavHost(
        navController = postsNavController,
        startDestination = Posts
    ) {
        composable<Posts> {
            PostsScreen(postsNavController)
        }

        composable<PostDetails>(typeMap = mapOf(typeOf<SearchParameters>() to SearchParametersType)) { backStackEntry ->
            val bookDetail = backStackEntry.toRoute<PostDetails>()
            PostDetailsScreen(
                searchParameters = bookDetail.parameters,
                onBack = postsNavController::popBackStack
            )
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