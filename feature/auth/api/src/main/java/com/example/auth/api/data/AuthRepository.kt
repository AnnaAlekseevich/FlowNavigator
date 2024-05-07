package com.example.auth.api.data

interface AuthRepository {

    suspend fun login(userName: String, password: String): Boolean
    suspend fun isUserAlreadyLoggedIn(): Boolean
}