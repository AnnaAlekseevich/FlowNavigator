package com.example.home.ui.bottombar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.api.BlogDestination
import com.example.api.PostsDestination
import com.example.api.ProfileDestination
import com.example.uikit.R

@Composable
fun BottomNavBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.LightGray,
        tonalElevation = 0.dp,
    ) {
        BlogBarItem(navController, currentDestination)
        PostsBarItem(navController, currentDestination)
        ProfileBarItem(navController, currentDestination)
    }
}

@Composable
private fun RowScope.BlogBarItem(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    val route = BlogDestination.blogContentRoute
    val selected = currentDestination?.route?.contains(BlogDestination.baseRoute) ?: false

    NavigationBarItem(
        selected = selected,
        onClick = {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            NavBarIcon(Icons.Default.Star)
        },
        label = {
            NavBarLabel(R.string.main_content_blog, isSelected = selected)
        },
//        colors = NavigationBarItemDefaults.colors(
//            selectedIconColor = Color.Black,
//            selectedTextColor = Color.Black,
//            indicatorColor = Color.LightGray,
//            unselectedTextColor = Color.Gray,
//            unselectedIconColor = Color.Gray,
//        )
    )
}

@Composable
private fun RowScope.PostsBarItem(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    val route = PostsDestination.postsRoute
    val selected = currentDestination?.route?.contains(PostsDestination.baseRoute) ?: false

    NavigationBarItem(
        selected = selected,
        onClick = {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            NavBarIcon(Icons.Default.Edit)
        },
        label = {
            NavBarLabel(R.string.main_content_posts, isSelected = selected)
        },
    )
}

@Composable
private fun RowScope.ProfileBarItem(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    val route = ProfileDestination.profileRoute
    val selected = currentDestination?.route?.contains(ProfileDestination.baseRoute) ?: false
    NavigationBarItem(
        selected = selected,
        onClick = {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            NavBarIcon(Icons.Default.AccountCircle)
        },
        label = {
            NavBarLabel(R.string.main_content_profile, isSelected = selected)
        },
    )
}

@Composable
private fun NavBarIcon(imageVector: ImageVector) {
    Icon(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .size(32.dp)
            .padding(2.dp),
        imageVector = imageVector,
        contentDescription = null
    )
}

@Composable
private fun NavBarLabel(@StringRes textRes: Int, isSelected: Boolean) {
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    Text(text = stringResource(textRes), fontWeight = fontWeight)
}