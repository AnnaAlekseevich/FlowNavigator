package com.example.auth.api.domain

interface LogInInteractor {

    suspend fun invoke(userName: String, password: String): Boolean
}