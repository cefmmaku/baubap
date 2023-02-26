package com.carlosFrias.baubapTest.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosFrias.baubapTest.data.login.UserInfo
import com.carlosFrias.baubapTest.domain.login.ValidateLoginCredentialsUseCase
import com.carlosFrias.baubapTest.presentation.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val validateLoginCredentialsUseCase: ValidateLoginCredentialsUseCase): ViewModel() {

    private val _userInfo = MutableLiveData<Event<UserInfo>>()
    val userInfo: LiveData<Event<UserInfo>> =  _userInfo
    fun validateCredentials(userName: String, password: String) {
        viewModelScope.launch {
            _userInfo.postValue(
                Event(validateLoginCredentialsUseCase.invoke(userName, password))
            )
        }
    }
}