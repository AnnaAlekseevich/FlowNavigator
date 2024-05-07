package com.example.auth.api.data

internal object AuthFakeRepository : AuthRepository {

    private var isUserLoggedIn = false

    override suspend fun login(userName: String, password: String): Boolean {
        //todo add real login. By default we think that user credentials are right
        isUserLoggedIn = true
        return true
    }

    override suspend fun isUserAlreadyLoggedIn(): Boolean {
        return isUserLoggedIn
    }
}