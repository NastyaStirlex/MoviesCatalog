package com.example.moviescatalog.screens.SignInScreen.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.Event
import com.example.moviescatalog.data.dto.JwtTokenDto
import com.example.moviescatalog.data.repository.AccountRepository
import com.example.moviescatalog.screens.SignInScreen.toLoginBodyDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AccountRepository
    )  : ViewModel() {

    val screenState = MutableLiveData<Event<JwtTokenDto>>()

    private val _signInStateData = MutableLiveData<SignInState>()
    val signInStateData: LiveData<SignInState>
        get() = _signInStateData

    private val _loginData = MutableLiveData<String>()
    val loginData: LiveData<String>
        get() = _loginData

    private val _passwordData = MutableLiveData<String>()
    val passwordData: LiveData<String>
        get() = _passwordData

    private val _fieldsAreCorrect = MutableLiveData(false)
    val fieldsAreCorrect: LiveData<Boolean>
        get() = _fieldsAreCorrect

    fun loginChanged(newValue: String) {
        _loginData.value = newValue
        validateEmpty()
    }

    fun passwordChanged(newValue: String) {
        _passwordData.value = newValue
        validateEmpty()
    }

    private fun validateEmpty() {
        _fieldsAreCorrect.value = _loginData.value?.isNotBlank() == true && _passwordData.value?.isNotBlank() == true
    }

    fun onClickLogin() = viewModelScope.launch {
        repository.login(toLoginBodyDto(_loginData.value!!, _passwordData.value!!), screenState)
    }
}