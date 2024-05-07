package com.example.api

object AuthDestination : NavigationDestination {
    const val authContent = "authContent"

    override val baseRoute: String = "auth"

    val authContentRoute: String = "$baseRoute/$authContent"

}

object MainDestination : NavigationDestination {
    private const val main: String = "mainContent"
    override val baseRoute: String = "main"

    val mainRoute: String = "$baseRoute/$main"
}

object BlogDestination : NavigationDestination {
    private const val blogContent = "blogContent"

    override val baseRoute: String = "blog"
    val blogContentRoute: String = "$baseRoute/$blogContent"
}

object PostsDestination : NavigationDestination {
    private const val postsData = "postsData"

    override val baseRoute: String = "posts"
    val postsRoute: String = "$baseRoute/$postsData"
}

object ProfileDestination : NavigationDestination {
    private const val profileData = "profileData"
    private const val profileSettings = "profileSettings"
    const val idArg = "idArg"

    override val baseRoute: String = "profile"
    val profileRoute: String = "$baseRoute/$profileData"
    val profileSettingsRoute: String = "$baseRoute/$profileSettings/{$idArg}"
}