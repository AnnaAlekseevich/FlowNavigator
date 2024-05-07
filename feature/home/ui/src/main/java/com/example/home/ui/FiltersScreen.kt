package com.example.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.theme.FlowNavigatorTheme

@Composable
fun FiltersScreen() {
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 160.dp), contentAlignment = Alignment.Center) {
        Text(text = "FiltersScreen")
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_FiltersScreen() {
    FlowNavigatorTheme {
        Text(text = "FiltersScreen")
    }
}