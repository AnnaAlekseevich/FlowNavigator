package com.example.home.ui.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildDestinationTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateUp: () -> Unit,
    @DrawableRes icon: Int?
) {
    TopAppBar(
        modifier = modifier.shadow(
            elevation = 3.dp,
            spotColor = Color.DarkGray,
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigateUp()
            }) {
                if (icon != null) {
                    Icon(painter = painterResource(id = icon), contentDescription = "Back")
                } else {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Title"
                    )
                }
            }
        }
    )
}