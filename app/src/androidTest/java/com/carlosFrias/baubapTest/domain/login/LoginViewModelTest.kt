package com.carlosFrias.baubapTest.domain.login

import com.carlosFrias.baubapTest.data.login.LoginRepository
import com.carlosFrias.baubapTest.data.login.UserInfo
import com.carlosFrias.baubapTest.presentation.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    companion object {
        const val INCORRECT_NAME = "Toby"
        const val INCORRECT_PASSWORD = "passwordsito"

        const val EMPTY_NAME = ""
        const val EMPTY_PASSWORD = ""

        const val CORRECT_NAME = "Carlos"
        const val CORRECT_PASSWORD = "Doggo"
    }

    private val validate = object: ValidateLoginCredentialsUseCase {
        override suspend fun invoke(name: String, password: String): UserInfo {
            return LoginRepository().executeLogin(name, password)
        }
    }

    private lateinit var loginViewModel : LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(validate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validateLogin_returnsNonRegisteredUser_wrongData() = runTest  {
        loginViewModel.validateCredentials(userName = INCORRECT_NAME, password = INCORRECT_PASSWORD)

        loginViewModel.userInfo.value?.getContentIfNotHandled()?.let {
                assert(!it.isRegistered)
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validateLogin_returnsNonRegisteredUser_emptyData() = runTest  {
        loginViewModel.validateCredentials(userName = EMPTY_NAME, password = EMPTY_PASSWORD)

        loginViewModel.userInfo.value?.getContentIfNotHandled()?.let {
            assert(!it.isRegistered)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validateLogin_returnsRegisteredUser_correctData() = runTest {
        loginViewModel.validateCredentials(userName = CORRECT_NAME, password = CORRECT_PASSWORD)

        loginViewModel.userInfo.value?.getContentIfNotHandled()?.let {
            assert(it.isRegistered)
        }
    }
}