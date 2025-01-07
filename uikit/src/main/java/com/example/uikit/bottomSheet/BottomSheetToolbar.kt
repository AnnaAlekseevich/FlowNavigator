@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.uikit.bottomSheet

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.uikit.R

sealed class ToolbarNavigation {
    data class Back(
        val onClick: () -> Unit,
        val enabled: Boolean = true,
    ) : ToolbarNavigation()

    data class Close(
        val onClick: () -> Unit,
        val enabled: Boolean = true,
    ) : ToolbarNavigation()

    data class Custom(
        val content: @Composable () -> Unit,
    ) : ToolbarNavigation()
}

@Composable
fun resolveToolbarColor(
    scrollBehavior: TopAppBarScrollBehavior,
    colors: TopAppBarColors? = null,
): Color {
    val colorTransitionFraction = scrollBehavior.state.overlappedFraction
    val fraction = if (colorTransitionFraction > 0.01f) 1f else 0f
    return resolveToolbarColor(fraction, colors)
}

val topAppBarColors: TopAppBarColors
    @Composable get() =
        TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFAF8F7),
            scrolledContainerColor = Color(0xFFFCE5DC),
            navigationIconContentColor = Color(0xFFED0334),
            titleContentColor = Color(0xFF2B140E),
            actionIconContentColor = Color(0xFFED0334),
        )

@Composable
fun resolveToolbarColor(
    fraction: Float,
    colors: TopAppBarColors?,
): Color {
    @Composable
    fun containerColor(fraction: Float) =
        lerp(
            (colors ?: topAppBarColors).containerColor,
            (colors ?: topAppBarColors).scrolledContainerColor,
            FastOutLinearInEasing.transform(fraction),
        )

    val appBarContainerColor by animateColorAsState(
        targetValue = containerColor(fraction),
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "StatusBarColorAnimation",
    )
    return appBarContainerColor
}

@Composable
fun BottomSheetToolbar(
    title: String = "",
    onCloseClick: (() -> Unit)? = null,
    navigation: ToolbarNavigation? = onCloseClick?.let { ToolbarNavigation.Close(onClick = onCloseClick) },
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = topAppBarColors,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    scrollState: Boolean? = null,
    isTwoLines: Boolean = false,
) {
    val toolbarColor = resolveToolbarColor(scrollBehavior, colors)

    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(
                color = toolbarColor,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_overlay_handle),
            contentDescription = null,
            modifier = Modifier.padding(vertical = 8.dp),
        )

        ToolbarInternal(
            title = title,
            navigation = navigation,
            actions = actions,
            colors = colors,
            toolbarColor = scrollState?.let { toolbarColor },
            scrollBehavior = scrollBehavior,
            isTwoLines = isTwoLines,
        )
    }
}

@Composable
private fun ToolbarInternal(
    title: String,
    navigation: ToolbarNavigation?,
    actions: @Composable RowScope.() -> Unit,
    colors: TopAppBarColors,
    toolbarColor: Color?,
    scrollBehavior: TopAppBarScrollBehavior,
    isTwoLines: Boolean,
) {
    val currentColor =
        toolbarColor?.let {
            colors.copy(containerColor = toolbarColor)
        } ?: colors

    val titleComposable = @Composable {
        Text(
            text = title,
            modifier =
            Modifier
                .fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

    val modifier =
        Modifier
            .fillMaxWidth()

    val navigationIconComposable = @Composable {
        when (navigation) {
            is ToolbarNavigation.Back -> {
                ToolbarCloseAction(
                    onClick = navigation.onClick,
                    enabled = navigation.enabled,
                    tint = currentColor.navigationIconContentColor
                )
            }

            is ToolbarNavigation.Close -> {
                ToolbarCloseAction(
                    onClick = navigation.onClick,
                    enabled = navigation.enabled,
                    tint = currentColor.navigationIconContentColor
                )
            }

            is ToolbarNavigation.Custom -> {
                navigation.content()
            }

            null -> {}
        }
    }

    val windowInsets = WindowInsets(top = 0.dp, bottom = 0.dp)

    if (isTwoLines) {
        MediumTopAppBar(
            title = titleComposable,
            modifier = modifier,
            navigationIcon = navigationIconComposable,
            actions = actions,
            windowInsets = windowInsets,
            colors = currentColor,
            scrollBehavior = scrollBehavior,
        )
    } else {
        TopAppBar(
            title = titleComposable,
            modifier = modifier,
            navigationIcon = navigationIconComposable,
            actions = actions,
            windowInsets = windowInsets,
            colors = currentColor,
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
fun ToolbarCloseAction(
    onClick: () -> Unit,
    enabled: Boolean = true,
    tint: Color? = null,
) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_24),
            tint = tint ?: Color.Black,
            contentDescription = ""
        )
    }
}

