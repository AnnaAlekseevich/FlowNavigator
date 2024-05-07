package com.example.flownavigator.ui.appcontent.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@Composable
fun AppContent(
) {
    val navController = rememberNavController()

    CompositionLocalProvider {
        Scaffold(
            containerColor = Color.White,
            contentWindowInsets = WindowInsets(
                top = 0.dp,
                bottom = 0.dp,
            ),
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppNavHost(navController = navController)
            }
        }
    }
}

