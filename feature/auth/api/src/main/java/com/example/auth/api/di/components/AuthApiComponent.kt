package com.example.auth.api.di.components

import com.example.auth.api.domain.LogInInteractor

interface AuthApiComponent {

    fun getLoginInteractor(): LogInInteractor

}