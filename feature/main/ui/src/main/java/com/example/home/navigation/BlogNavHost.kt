package com.example.home.navigation

import android.util.Log
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
import com.example.api.BlogDestination
import com.example.home.ui.toolbar.TopBarViewState

@Composable
fun BlogNavHost(topBarStateListener: (TopBarViewState) -> Unit) {
    topBarStateListener.invoke(
        TopBarViewState.UserRootTopBar()
    )

    val blogNavController = rememberNavController()

    NavHost(blogNavController, startDestination = "${BlogDestination.blogContentRoute}1") {
        for (i in 1..10) {
            composable("${BlogDestination.blogContentRoute}$i") {
                BlogScreen(
                    text = "Blog $i",
                    onNextClick = {
                        if (i < 10) {
                            blogNavController.navigate("${BlogDestination.blogContentRoute}${i + 1}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BlogScreen(
    text: String,
    onNextClick: () -> Unit
) {
    Log.d("CheckScreen", "BlogScreen")
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