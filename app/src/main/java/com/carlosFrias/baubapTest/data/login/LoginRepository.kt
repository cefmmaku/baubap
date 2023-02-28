package com.carlosFrias.baubapTest.data.login

import javax.inject.Inject

class LoginRepository @Inject constructor() {

    suspend fun executeLogin(name: String, password: String) : UserInfo {
        val defaultUser = UserInfo()
        val newUser = UserInfo(name = name, password = password, isRegistered = false)
        return if(newUser.name == defaultUser.name){
            defaultUser
        } else {
            newUser
        }
    }

}