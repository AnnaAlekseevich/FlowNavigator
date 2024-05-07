package com.example.auth.api.di.components

//import com.example.auth.api.di.modules.AuthModule
import com.example.auth.api.domain.LogInInteractor
//import dagger.Component

interface AuthApiComponent {

    fun getLoginInteractor(): LogInInteractor

}

//@Component(
//    modules = [AuthModule::class],
//)
//internal interface AuthApiInternalComponent :
//    AuthApiComponent {
//
//}

//object AuthApiHolder {
//    fun getComponent(): AuthApiComponent {
//        return DaggerAuthApiInternalComponent.builder()
//            .build()
//    }
//}
