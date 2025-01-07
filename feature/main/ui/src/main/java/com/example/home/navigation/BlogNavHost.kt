package com.example.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.api.Blog
import com.example.api.Blog1
import com.example.api.Blog2
import com.example.api.Blog3
import com.example.api.LinkPatterns
import com.example.blog.ui.BlogScreen

@Composable
fun BlogNavHost() {
    val blogNavController = rememberNavController()

    val blogScreens = listOf(Blog, Blog1, Blog2, Blog3)

    val uri = LinkPatterns.uri

    NavHost(blogNavController, startDestination = Blog) {
        blogScreens.forEachIndexed { index, screen ->
            when (screen) {
                is Blog -> composable<Blog> {
                    BlogScreen(
                        text = "Blog ${index + 1}",
                        onNextClick = {
                            if (index < blogScreens.size - 1) {
                                blogNavController.navigate(blogScreens[index + 1])
                            }
                        }
                    )
                }
                is Blog1 -> composable<Blog1> {
                    BlogScreen(
                        text = "Blog ${index + 1}",
                        onNextClick = {
                            if (index < blogScreens.size - 1) {
                                blogNavController.navigate(blogScreens[index + 1])
                            }
                        },
                        onBack = blogNavController::popBackStack
                    )
                }
                is Blog2 -> composable<Blog2> {
                    BlogScreen(
                        text = "Blog ${index + 1}",
                        onNextClick = {
                            if (index < blogScreens.size - 1) {
                                blogNavController.navigate(blogScreens[index + 1])
                            }
                        },
                        onBack = blogNavController::popBackStack
                    )
                }
                is Blog3 -> composable<Blog3> (deepLinks = listOf(navDeepLink { uriPattern = "$uri/blog" })) {
                    BlogScreen(
                        text = "Blog ${index + 1}",
                        onNextClick = {
                            if (index < blogScreens.size - 1) {
                                blogNavController.navigate(blogScreens[index + 1])
                            }
                        },
                        onBack = blogNavController::popBackStack
                    )
                }
            }
        }
    }
}
