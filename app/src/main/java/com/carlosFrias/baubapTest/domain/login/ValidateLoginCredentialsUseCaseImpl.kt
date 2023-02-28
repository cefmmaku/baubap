package com.carlosFrias.baubapTest.domain.login

import com.carlosFrias.baubapTest.data.login.LoginRepository
import com.carlosFrias.baubapTest.data.login.UserInfo
import javax.inject.Inject

class ValidateLoginCredentialsUseCaseImpl @Inject constructor(private val loginRepository: LoginRepository) : ValidateLoginCredentialsUseCase {
    override suspend fun invoke(name: String, password: String): UserInfo {
        return loginRepository.executeLogin(name = name, password = password)
    }
}