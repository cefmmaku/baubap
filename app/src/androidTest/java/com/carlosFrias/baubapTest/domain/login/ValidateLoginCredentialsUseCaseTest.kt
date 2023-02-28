package com.carlosFrias.baubapTest.domain.login

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class ValidateLoginCredentialsUseCaseTest {

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

    @Inject
    lateinit var validateLoginCredentialsUseCase : ValidateLoginCredentialsUseCase

    @Before
    fun setup() {
        rule.inject()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validateLogin_returnsUnregisteredUserInfoObject_wrongData() = runTest {
        val gottenUserInfo = validateLoginCredentialsUseCase.invoke(INCORRECT_NAME, INCORRECT_PASSWORD)
        assertFalse(gottenUserInfo.isRegistered)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validateLogin_returnsUnregisteredUserInfoObject_emptyData() = runTest {
        val gottenUserInfo = validateLoginCredentialsUseCase.invoke(EMPTY_NAME, EMPTY_PASSWORD)
        assertFalse(gottenUserInfo.isRegistered)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun validateLogin_returnsRegisteredUserInfoObject_correctData() = runTest {
        val gottenUserInfo = validateLoginCredentialsUseCase.invoke(CORRECT_NAME, CORRECT_PASSWORD)
        assertTrue(gottenUserInfo.isRegistered)
    }
}