package com.example.posts.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.api.PostDetails
import com.example.api.SearchParameters
import com.example.uikit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(postsNavController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.main_content_posts)) },
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val parameters = SearchParameters("query!", listOf("filter1", "filter2"))

                Button(onClick = {
                    postsNavController.navigate(PostDetails(parameters))
                }) {
                    Text(text = stringResource(id = R.string.profile_go_to_posts_details))
                }
            }
        }
    )
}