package com.example.home.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.api.Blog
import com.example.api.Blog1
import com.example.api.Blog2
import com.example.api.Blog3
import com.example.home.ui.toolbar.TopBarViewState

@Composable
fun BlogNavHost(topBarStateListener: (TopBarViewState) -> Unit) {
    topBarStateListener.invoke(
        TopBarViewState.UserRootTopBar()
    )

    val blogNavController = rememberNavController()

    val blogScreens = listOf(Blog, Blog1, Blog2, Blog3)

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
                        }
                    )
                }
                is Blog2 -> composable<Blog2> {
                    BlogScreen(
                        text = "Blog ${index + 1}",
                        onNextClick = {
                            if (index < blogScreens.size - 1) {
                                blogNavController.navigate(blogScreens[index + 1])
                            }
                        }
                    )
                }
                is Blog3 -> composable<Blog3> {
                    BlogScreen(
                        text = "Blog ${index + 1}",
                        onNextClick = {
                            if (index < blogScreens.size - 1) {
                                blogNavController.navigate(blogScreens[index + 1])
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BlogScreen(
    text: String,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onNextClick) {
            Text("Next")
        }
    }
}