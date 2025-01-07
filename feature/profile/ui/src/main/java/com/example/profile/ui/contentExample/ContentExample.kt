package com.example.profile.ui.contentExample

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ContentExample() {
    val viewModel: ContentExampleViewModel = hiltViewModel()
    val items = viewModel.simpleList

    items.forEach {
        Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), text = it)
    }
}
