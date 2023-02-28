package com.carlosFrias.baubapTest.domain.login

import com.carlosFrias.baubapTest.data.login.LoginRepository
import com.carlosFrias.baubapTest.data.login.UserInfo
import com.carlosFrias.baubapTest.presentation.login.LoginViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginViewModelTest {

    companion object {
        const val INCORRECT_NAME = "Toby"
        const val INCORRECT_PASSWORD = "passwordsito"

        const val EMPTY_NAME = ""
        const val EMPTY_PASSWORD = ""

        const val CORRECT_NAME = "Carlos"
        const val CORRECT_PASSWORD = "Doggo"
    }

    @get:Rule
    val rule = HiltAndroidRule(this)

    private val validate = object: ValidateLoginCredentialsUseCase {
        override suspend fun invoke(name: String, password: String): UserInfo {
            return LoginRepository().executeLogin(name, password)
        }
    }

    var loginViewModel = LoginViewModel(validate)

    @Before
    fun setUp() {
        rule.inject()
    }

    @Test
    fun validateLogin_returnsNonRegisteredUser_wrongData()  {
        loginViewModel.validateCredentials(userName = INCORRECT_NAME, password = INCORRECT_PASSWORD)

        loginViewModel.userInfo.value?.getContentIfNotHandled()?.let {
                assert(!it.isRegistered)
            }
    }

    @Test
    fun validateLogin_returnsNonRegisteredUser_emptyData()  {
        loginViewModel.validateCredentials(userName = EMPTY_NAME, password = EMPTY_PASSWORD)

        loginViewModel.userInfo.value?.getContentIfNotHandled()?.let {
            assert(!it.isRegistered)
        }
    }

    @Test
    fun validateLogin_returnsRegisteredUser_correctData()  {
        loginViewModel.validateCredentials(userName = CORRECT_NAME, password = CORRECT_PASSWORD)

        loginViewModel.userInfo.value?.getContentIfNotHandled()?.let {
            assert(!it.isRegistered)
        }
    }
}