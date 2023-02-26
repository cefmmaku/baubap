package com.carlosFrias.baubapTest.domain.login

import com.carlosFrias.baubapTest.data.login.UserInfo

interface ValidateLoginCredentialsUseCase {
    operator fun invoke(name: String, password: String): UserInfo
}