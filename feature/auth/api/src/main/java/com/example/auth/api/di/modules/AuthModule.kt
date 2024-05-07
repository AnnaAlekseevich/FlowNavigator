package com.example.auth.api.di.modules

import com.example.auth.api.data.AuthFakeRepository
import com.example.auth.api.data.LogInInteractorImpl
import com.example.auth.api.data.AuthRepository
import com.example.auth.api.domain.LogInInteractor
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
//
//@InstallIn(SingletonComponent::class)
//@Module
//internal interface AuthModule {
//
//    @Binds
//    fun bindLoginInteractor(impl: LogInInteractorImpl): LogInInteractor
//
//    @Binds
//    fun bindAuthRepository(impl: AuthFakeRepository): AuthRepository
//
//
//}