package com.example.home.ui

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.api.Blog
import com.example.api.Posts
import com.example.api.Profile
import com.example.api.ProfileSettings
import com.example.api.Test
import com.example.home.navigation.BlogNavHost
import com.example.home.navigation.PostsNavHost
import com.example.home.navigation.ProfileNavHost
import kotlinx.coroutines.delay

@Composable
fun MainAppContentScreen() {
    val blogNavController = rememberNavController()
    val postsNavController = rememberNavController()
    val profileNavController = rememberNavController()

    val selectedTab = remember { mutableStateOf<Any>(Blog) }
    val tabBackStack = remember { mutableStateListOf<Any>() }
    val context = LocalContext.current

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }

    HandleBackPress(selectedTab, tabBackStack, blogNavController, postsNavController, profileNavController, context)
    HandleDeepLinks(context, selectedTab, tabBackStack, profileNavController)

    Scaffold(
        topBar = {},
        contentWindowInsets = WindowInsets(top = 0.dp, bottom = 0.dp),
    ) { padding ->
        NavigationSuiteScaffold(
            modifier = Modifier.padding(padding),
            layoutType = customNavSuiteType,
            navigationSuiteItems = {
                listOf(Blog, Posts, Profile).forEach { navItem ->
                    item(
                        modifier = Modifier.wrapContentWidth(),
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                                    .size(32.dp)
                                    .padding(2.dp),
                                imageVector = when (navItem) {
                                    Blog -> Icons.Default.Star
                                    Posts -> Icons.Default.Edit
                                    Profile -> Icons.Default.AccountCircle
                                    else -> Icons.Default.Star
                                },
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                text = navItem::class.simpleName ?: ""
                            )
                        },
                        selected = selectedTab.value == navItem,
                        onClick = {
                            if (navItem != selectedTab.value) {
                                if (!tabBackStack.contains(selectedTab.value)) {
                                    tabBackStack.add(selectedTab.value)
                                }
                                selectedTab.value = navItem
                            }
                        }
                    )
                }
            }
        ) {
            Row(Modifier.fillMaxSize()) {
                when (selectedTab.value) {
                    Blog -> BlogNavHost(blogNavController)
                    Posts -> PostsNavHost(postsNavController)
                    Profile -> ProfileNavHost(profileNavController)
                }
            }
        }
    }
}

@Composable
fun HandleBackPress(
    selectedTab: MutableState<Any>,
    tabBackStack: MutableList<Any>,
    blogNavController: NavHostController,
    postsNavController: NavHostController,
    profileNavController: NavHostController,
    context: Context
) {
    BackHandler(true) {
        when {
            canPopBackStack(selectedTab, blogNavController, postsNavController, profileNavController) -> {
                popBackStackForSelectedTab(selectedTab, blogNavController, postsNavController, profileNavController)
            }
            tabBackStack.isNotEmpty() -> selectedTab.value = tabBackStack.removeAt(tabBackStack.lastIndex)
            else -> (context as? Activity)?.finish()
        }
    }
}

@Composable
fun HandleDeepLinks(
    context: Context,
    selectedTab: MutableState<Any>,
    tabBackStack: MutableList<Any>,
    profileNavController: NavHostController
) {
    LaunchedEffect((context as? Activity)?.intent) {
        (context as? Activity)?.intent?.data?.toString()?.let { data ->
            when {
                "bottomSheet" in data -> navigateToProfileTab(selectedTab, tabBackStack, profileNavController)
                "profile" in data -> handleProfileDeepLink(data, selectedTab, tabBackStack, profileNavController)
                "blog" in data -> navigateToTabWithBackStack(Blog, selectedTab, tabBackStack)
                "posts" in data -> navigateToTabWithBackStack(Posts, selectedTab, tabBackStack)
            }
            (context as? Activity)?.intent = null
        }
    }
}

fun canPopBackStack(
    selectedTab: Any,
    blogNavController: NavHostController,
    postsNavController: NavHostController,
    profileNavController: NavHostController
): Boolean {
    return when (selectedTab) {
        Blog -> blogNavController.previousBackStackEntry != null
        Posts -> postsNavController.previousBackStackEntry != null
        Profile -> profileNavController.previousBackStackEntry != null
        else -> false
    }
}

fun popBackStackForSelectedTab(
    selectedTab: Any,
    blogNavController: NavHostController,
    postsNavController: NavHostController,
    profileNavController: NavHostController
) {
    when (selectedTab) {
        Blog -> blogNavController.popBackStack()
        Posts -> postsNavController.popBackStack()
        Profile -> profileNavController.popBackStack()
    }
}

fun navigateToTabWithBackStack(
    newTab: Any,
    selectedTab: MutableState<Any>,
    tabBackStack: MutableList<Any>
) {
    if (selectedTab.value != newTab) {
        tabBackStack.add(selectedTab.value)
    }
    selectedTab.value = newTab
}

private suspend fun navigateToProfileTab(
    selectedTab: MutableState<Any>,
    tabBackStack: MutableList<Any>,
    profileNavController: NavHostController
) {
    navigateToTabWithBackStack(Profile, selectedTab, tabBackStack)
    delay(10)
    profileNavController.navigate(Profile) {
        launchSingleTop = true
        restoreState = true
        popUpTo(Profile) { saveState = true }
    }
}

private suspend fun handleProfileDeepLink(
    data: String,
    selectedTab: MutableState<Any>,
    tabBackStack: MutableList<Any>,
    profileNavController: NavHostController
) {
    navigateToTabWithBackStack(Profile, selectedTab, tabBackStack)
    delay(10)

    val pathSegments = Uri.parse(data).pathSegments
    val idArg = pathSegments?.getOrNull(1)?.toIntOrNull() ?: 0
    val openTestScreen = pathSegments?.getOrNull(2) == "test"

    profileNavController.navigate(ProfileSettings(idArg)) {
        launchSingleTop = true
        restoreState = true
    }

    if (openTestScreen) {
        profileNavController.navigate(Test) {
            launchSingleTop = true
            restoreState = true
        }
    }
}