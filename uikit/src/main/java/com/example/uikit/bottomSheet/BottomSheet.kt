package com.example.uikit.bottomSheet

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(title: String, onBack: () -> Unit, content: @Composable () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current.density
    val screenHeightPx = LocalConfiguration.current.screenHeightDp * density
    val dragOffsetY = remember { Animatable(screenHeightPx) }
    val closeThreshold = screenHeightPx / 4f
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var isAnimating by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(true) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (isVisible) 0.25f else 0f,
        animationSpec = tween(durationMillis = 100), label = ""
    )

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                if (delta > 0 && scrollState.value == 0) {
                    coroutineScope.launch {
                        val newOffset = (dragOffsetY.value + delta).coerceIn(0f, screenHeightPx)
                        dragOffsetY.snapTo(newOffset)
                    }
                    return Offset(0f, delta)
                }
                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                if (available.y > 0 && dragOffsetY.value > closeThreshold && !isAnimating) {
                    coroutineScope.launch {
                        isAnimating = true
                        dragOffsetY.animateTo(screenHeightPx, tween(150))
                        onBack()
                        isAnimating = false
                    }
                }
                return super.onPreFling(available)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (dragOffsetY.value > closeThreshold && !isAnimating) {
                    isAnimating = true
                    dragOffsetY.animateTo(screenHeightPx, tween(150))
                    onBack()
                    isAnimating = false
                } else {
                    dragOffsetY.animateTo(0f, tween(200))
                }
                return available
            }
        }
    }

    if (isVisible) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = alphaAnim.value))
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            coroutineScope.launch {
                                dragOffsetY.animateTo(screenHeightPx, tween(300))
                                isVisible = false
                                delay(300)
                                onBack()
                            }
                        }
                    )
                }
                .nestedScroll(nestedScrollConnection),
            contentAlignment = Alignment.BottomCenter
        ) {
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    dragOffsetY.animateTo(0f, tween(300))
                }
            }

            Box(
                modifier = Modifier
                    .offset { IntOffset(0, dragOffsetY.value.toInt()) }
                    .background(Color.Transparent)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    BottomSheetToolbar(
                        title = title,
                        onCloseClick = {
                            coroutineScope.launch {
                                dragOffsetY.animateTo(screenHeightPx, tween(300))
                                isVisible = false
                                delay(300)
                                onBack.invoke()
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                            .background(Color(0xFFFAF8F7))
                            .padding(16.dp)
                    ) {
                        content.invoke()
                    }
                }
            }
        }
    }
}