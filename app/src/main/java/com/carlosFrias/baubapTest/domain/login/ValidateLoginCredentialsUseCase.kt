package com.carlosFrias.baubapTest.domain.login

import com.carlosFrias.baubapTest.data.login.UserInfo

interface ValidateLoginCredentialsUseCase {
    suspend operator fun invoke(name: String, password: String): UserInfo
}