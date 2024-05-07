package com.example.blog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uikit.theme.FlowNavigatorTheme

@Composable
fun BlogScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "BlogScreen")
    }
}

@Preview(showBackground = true)
@Composable
fun BlogScreenPreview() {
    FlowNavigatorTheme {
        BlogScreen()
    }
}