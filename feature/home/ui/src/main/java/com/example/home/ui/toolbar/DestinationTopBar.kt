package com.example.home.ui.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.home.ui.toolbar.model.UserUiModel

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    userUiModelInfo: UserUiModel,
    topBarViewState: TopBarViewState
) {

    when (topBarViewState) {
        is TopBarViewState.UserRootTopBar -> {
            RootDestinationTopBar(
                modifier = modifier,
                userUiModelInfo = userUiModelInfo,
                menuItems = (topBarViewState as? TopBarViewState.VisibleTopBar)?.menuItems
            )
        }

        is TopBarViewState.ChildTopBar -> {
            ChildDestinationTopBar(
                title = (topBarViewState as? TopBarViewState.ChildTopBar)?.title ?: "",
                onNavigateUp = {
                    (topBarViewState as? TopBarViewState.VisibleTopBar)?.homeActionClick?.invoke()
                },
                icon = (topBarViewState as? TopBarViewState.ChildTopBar)?.homeImageResId
            )
        }

        is TopBarViewState.VisibleTopBar -> {
            RootDestinationTopBar(
                modifier = modifier,
                userUiModelInfo = userUiModelInfo,
                menuItems = (topBarViewState as? TopBarViewState.VisibleTopBar)?.menuItems
            )
        }

        is TopBarViewState.NoTopBar -> {
        }
    }
}