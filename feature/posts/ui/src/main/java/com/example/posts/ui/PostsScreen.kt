package com.example.posts.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.api.SearchParameters
import kotlinx.serialization.json.Json

@Composable
fun PostsScreen(postsNavController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "PostsScreen")
        val parameters = SearchParameters("query!", listOf("filter1", "filter2"))

        Button(onClick = {
            val searchArgument =
                Uri.encode(Json.encodeToString(SearchParameters.serializer(), parameters))
            val route = "postDetails/$searchArgument"
            postsNavController.navigate(route)
        }) {
            Text(text = stringResource(id = com.example.uikit.R.string.profile_go_to_posts_details))
        }
    }
}