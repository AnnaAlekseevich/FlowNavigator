package com.example.auth.api.data

import com.example.auth.api.domain.LogInInteractor


internal class LogInInteractorImpl constructor(
    private val authRepository: AuthRepository
) : LogInInteractor {
    override suspend fun invoke(userName: String, password: String): Boolean {
        return authRepository.login(userName, password)
    }
}