package com.example.moviescatalog.screens.SignUpScreen.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.Event
import com.example.moviescatalog.data.dto.JwtTokenDto
import com.example.moviescatalog.data.repository.AccountRepository
import com.example.moviescatalog.screens.SignInScreen.models.Status
import com.example.moviescatalog.screens.SignInScreen.models.ValidationEvent
import com.example.moviescatalog.screens.SignInScreen.toLoginBodyDto
import com.example.moviescatalog.screens.SignUpScreen.toRegisterBodyDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

    val screenState = MutableLiveData<Event<JwtTokenDto>>()

    private val _loginState = mutableStateOf("")
    val loginState: State<String>
        get() = _loginState

    private val _loginErrorState = mutableStateOf(true)
    val loginErrorState: State<Boolean>
        get() = _loginErrorState

    private val _emailState = mutableStateOf("")
    val emailState: State<String>
        get() = _emailState

    private val _emailErrorState = mutableStateOf(true)
    val emailErrorState: State<Boolean>
        get() = _emailErrorState

    private val _nameState = mutableStateOf("")
    val nameState: State<String>
        get() = _nameState

    private val _nameErrorState = mutableStateOf(true)
    val nameErrorState: State<Boolean>
        get() = _nameErrorState

    private val _passwordState = mutableStateOf("")
    val passwordState: State<String>
        get() = _passwordState

    private val _passwordErrorState = mutableStateOf(true)
    val passwordErrorState: State<Boolean>
        get() = _passwordErrorState

    private val _checkPasswordState = mutableStateOf("")
    val checkPasswordState: State<String>
        get() = _checkPasswordState

    private val _checkPasswordErrorState = mutableStateOf(true)
    val checkPasswordErrorState: State<Boolean>
        get() = _checkPasswordErrorState

    private val _birthdateState = mutableStateOf<LocalDate?>(null)
    val birthdateState: State<LocalDate?>
        get() = _birthdateState

    private val _birthdateErrorState = mutableStateOf(true)
    val birthdateErrorState: State<Boolean>
        get() = _birthdateErrorState

    private val _genderMale = mutableStateOf(false)
    val genderMale: State<Boolean>
        get() = _genderMale

    private val _genderFemale = mutableStateOf(false)
    val genderFemale: State<Boolean>
        get() = _genderFemale

    private val _genderState = mutableStateOf("")
    val genderState: State<String>
        get() = _genderState

    private val _genderErrorState = mutableStateOf(true)
    val genderErrorState: State<Boolean>
        get() = _genderErrorState

    private val _correctnessFields = mutableStateOf(false)
    val correctnessFields: State<Boolean>
        get() = _correctnessFields

    fun loginChange(login: String) {
        _loginState.value = login
        isEnableOnClick()
    }

    fun loginErrorStateChange(state: Boolean) {
        _loginErrorState.value = state
        isEnableOnClick()
    }

    fun emailChange(email: String) {
        _emailState.value = email
        isEnableOnClick()
    }

    fun emailErrorStateChange(state: Boolean) {
        _emailErrorState.value = state
        isEnableOnClick()
    }

    fun birthdateChange(birthday: LocalDate?) {
        _birthdateState.value = birthday
        isEnableOnClick()
    }

    fun birthdateErrorStateChange(state: Boolean) {
        _birthdateErrorState.value = state
        isEnableOnClick()
    }

    fun nameChange(name: String) {
        _nameState.value = name
        isEnableOnClick()
    }

    fun nameErrorStateChange(state: Boolean) {
        _nameErrorState.value = state
        isEnableOnClick()
    }

    fun passwordChange(password: String) {
        _passwordState.value = password
        isEnableOnClick()
    }

    fun passwordErrorStateChange(state: Boolean) {
        _passwordErrorState.value = state
        isEnableOnClick()
    }

    fun checkPasswordChange(checkPassword: String) {
        _checkPasswordState.value = checkPassword
        isEnableOnClick()
    }

    fun checkPasswordErrorStateChange(state: Boolean) {
        _checkPasswordErrorState.value = state
        isEnableOnClick()
    }


    fun genderMaleChanged(newValue: Boolean) {
        _genderMale.value = newValue
        genderChange()
        isEnableOnClick()
    }

    fun genderFemaleChanged(newValue: Boolean) {
        _genderFemale.value = newValue
        genderChange()
        isEnableOnClick()
    }

    private fun genderChange() {
        _genderState.value = if(genderMale.value) "Male" else if(genderFemale.value) "Female" else ""
    }

    fun genderErrorStateChange(state: Boolean) {
        _genderErrorState.value = state
        isEnableOnClick()
    }


    private fun isEnableOnClick() {
        _correctnessFields.value = !_loginErrorState.value && !_emailErrorState.value && !_nameErrorState.value && !_passwordErrorState.value && !_checkPasswordErrorState.value && !_birthdateErrorState.value && !_genderErrorState.value
    }

    fun onRegisterClick() = viewModelScope.launch {
        repository.register(toRegisterBodyDto(
            username = _loginState.value,
            email = _emailState.value,
            name = _nameState.value,
            password = _passwordState.value,
            birthdate = _birthdateState.value!!,
            gender = _genderState.value
        ), state = screenState)
    }
}