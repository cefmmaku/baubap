package com.carlosFrias.baubapTest.domain.login

import com.carlosFrias.baubapTest.data.login.UserInfo
import javax.inject.Inject

class ValidateLoginCredentialsUseCaseImpl @Inject constructor() : ValidateLoginCredentialsUseCase {
    override fun invoke(name: String, password: String): UserInfo {
        val defaultUser = UserInfo()
        val newUser = UserInfo(name = name, password = password, isRegistered = false)
        return if(newUser.name == defaultUser.name){
            defaultUser
        } else {
            newUser
        }
    }
}