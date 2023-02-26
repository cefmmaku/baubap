package com.carlosFrias.baubapTest.domain.login

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginUseCaseModule {

    @Binds
    abstract fun provideValidateLoginCredentialsUseCase(validateLoginCredentialsUseCaseImpl: ValidateLoginCredentialsUseCaseImpl) : ValidateLoginCredentialsUseCase
}