package com.example.home.ui.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.home.ui.toolbar.model.UserUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootDestinationTopBar(
    modifier: Modifier = Modifier,
    userUiModelInfo: UserUiModel,
    menuItems: List<Pair<Int, () -> Unit>>? = null,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Gray,
            actionIconContentColor = Color.Black
        ),
        modifier = modifier.shadow(
            elevation = 5.dp,
            spotColor = Color.DarkGray,
        ),
        title = {
            Text(
                text = userUiModelInfo.userName,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
//                openDrawer()
            }) {
                if (userUiModelInfo.userAvatarUrl != null) {

                    Image(
                        modifier =
                        Modifier
                            .size(32.dp, 32.dp),
                        painter = rememberAsyncImagePainter(
                            model = userUiModelInfo.userAvatarUrl,
                            placeholder = rememberAsyncImagePainter(model = userUiModelInfo.userAvatarUrl),
                        ),
                        contentDescription = null,
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(32.dp, 32.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Bar icon"
                    )
                }
            }
        },
        actions = {
            Row {
                menuItems?.forEach { menuItem ->
                    Icon(
                        painter =
                        painterResource(
                            id = menuItem.first,
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(24.dp)
                            .clickable {
                                menuItem.second.invoke()
                            },
                    )
                }
            }
        }
    )
}