package com.example.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.api.MainDestination
import com.example.uikit.R

@Composable
fun AuthContent(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TextButton(content = { Text(text = stringResource(id = R.string.main_content_flow)) },
            onClick = {
                navController.navigate(route = MainDestination)
            })
    }
}
