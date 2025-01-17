package com.example.home.ui

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.api.Blog
import com.example.api.LinkPatterns
import com.example.api.Posts
import com.example.api.Profile
import com.example.home.navigation.BlogNavHost
import com.example.home.navigation.PostsNavHost
import com.example.home.navigation.ProfileNavHost

@Composable
fun MainAppContentScreen() {
    val navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val navItems = listOf(Blog, Posts, Profile)

    val currentDestination by remember {
        derivedStateOf {
            navBackStackEntry.value?.destination
        }
    }

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }

    val uri = LinkPatterns.uri

    Scaffold(
        topBar = {},
        contentWindowInsets =
        WindowInsets(
            top = 0.dp,
            bottom = 0.dp,
        ),
    ) { padding ->
        NavigationSuiteScaffold(
            modifier = Modifier.padding(padding),
            layoutType = customNavSuiteType,
            navigationSuiteItems = {
                navItems.forEach { navItem ->
                    item(
                        modifier = Modifier.wrapContentWidth(),
                        icon = {
                            when (navItem) {
                                is Blog -> {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 10.dp, end = 10.dp)
                                            .size(32.dp)
                                            .padding(2.dp),
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null
                                    )
                                }

                                is Posts -> {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 10.dp, end = 10.dp)
                                            .size(32.dp)
                                            .padding(2.dp),
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null
                                    )
                                }

                                is Profile -> {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 10.dp, end = 10.dp)
                                            .size(32.dp)
                                            .padding(2.dp),
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = null
                                    )
                                }

                                else -> {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 10.dp, end = 10.dp)
                                            .size(32.dp)
                                            .padding(2.dp),
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null
                                    )
                                }

                            }
                        },
                        label = { Text(modifier = Modifier.wrapContentWidth(), text = navItem::class.simpleName ?: "") },
                        selected = navItem::class.simpleName?.let {
                            currentDestination?.route?.contains(it)
                        } == true,
                        onClick = {
                            navController.navigate(navItem) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) {
            Row(
                Modifier
                    .fillMaxSize()
            ) {
                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = Blog
                ) {
                    composable<Blog> (deepLinks = listOf(navDeepLink { uriPattern = "$uri/blog" })) {
                        BlogNavHost()
                    }
                    composable<Posts> {
                        PostsNavHost()
                    }
                    composable<Profile>(deepLinks = listOf(navDeepLink { uriPattern = "$uri/profile/{idArg}" })) {
                        ProfileNavHost()
                    }
                }
            }
        }
    }
}