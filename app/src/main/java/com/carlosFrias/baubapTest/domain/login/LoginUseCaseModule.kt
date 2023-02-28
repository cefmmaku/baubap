package com.carlosFrias.baubapTest.domain.login

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginUseCaseModule {

    @Binds
    abstract fun provideValidateLoginCredentialsUseCase(validateLoginCredentialsUseCaseImpl: ValidateLoginCredentialsUseCaseImpl) : ValidateLoginCredentialsUseCase
}