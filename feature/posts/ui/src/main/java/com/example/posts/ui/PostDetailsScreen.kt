package com.example.posts.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.api.SearchParameters
import com.example.uikit.R

@Composable
fun PostDetailsScreen(searchParameters: SearchParameters) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.profile_posts_details) + " - " + searchParameters.searchQuery)
    }
}