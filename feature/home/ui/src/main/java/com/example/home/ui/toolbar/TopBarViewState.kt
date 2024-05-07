package com.example.home.ui.toolbar

import androidx.annotation.DrawableRes

interface TopBarViewState {

    open class VisibleTopBar(
        val title: String? = null,
        val homeActionClick: (() -> Unit)? = null,
        val menuItems: List<Pair<Int, () -> Unit>>? = null,
    ) : TopBarViewState


    class UserRootTopBar(
        userProfileClickListener: (() -> Unit)? = null,
        menuItems: List<Pair<Int, () -> Unit>>? = null,
    ) : VisibleTopBar(
        title = null,
        homeActionClick = userProfileClickListener,
        menuItems = menuItems,
    )

    class ChildTopBar(
        @DrawableRes val homeImageResId: Int,
        title: String = "",
        homeActionClick: () -> Unit = {},
        menuItems: List<Pair<Int, () -> Unit>>? = null,
    ) : VisibleTopBar(title, homeActionClick, menuItems)

    object NoTopBar : TopBarViewState

}