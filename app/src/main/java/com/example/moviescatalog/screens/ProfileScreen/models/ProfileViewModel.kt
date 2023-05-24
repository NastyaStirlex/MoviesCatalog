package com.example.moviescatalog.screens.ProfileScreen.models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.di.GetProfileCallback
import com.example.moviescatalog.data.dto.ProfileDto
import com.example.moviescatalog.data.repository.AccountRepository
import com.example.moviescatalog.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _profileStateDate = MutableLiveData<ProfileState>()
    val profileState: LiveData<ProfileState>
        get() = _profileStateDate

    private val _isProfileLoadingData = MutableLiveData<Boolean>()

    private val _loginState = mutableStateOf("")
    val loginState: State<String>
        get() = _loginState

    private val _emailState = mutableStateOf("")
    val emailState: State<String>
        get() = _emailState

    private val _emailErrorState = mutableStateOf(false)
    val emailErrorState: State<Boolean>
        get() = _emailErrorState

    private val _avatarUrlState = mutableStateOf("")
    val avatarUrlState: State<String>
        get() = _avatarUrlState

    private val _avatarUrlErrorState = mutableStateOf(false)
    val avatarUrlErrorState: State<Boolean>
        get() = _avatarUrlErrorState

    private val _nameState = mutableStateOf("")
    val nameState: State<String>
        get() = _nameState

    private val _nameErrorState = mutableStateOf(false)
    val nameErrorState: State<Boolean>
        get() = _nameErrorState

    private val _birthdateState = mutableStateOf<LocalDate>(LocalDate.MIN)
    val birthdateState: State<LocalDate?>
        get() = _birthdateState

    private val _birthdateErrorState = mutableStateOf(false)
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

    private val _genderErrorState = mutableStateOf(false)
    val genderErrorState: State<Boolean>
        get() = _genderErrorState

    private val _correctnessFields = mutableStateOf(false)
    val correctnessFields: State<Boolean>
        get() = _correctnessFields

    private var userId: String = ""

    fun emailChange(email: String) {
        _emailState.value = email
        isEnableOnClick()
    }

    fun emailErrorStateChange(state: Boolean) {
        _emailErrorState.value = state
        isEnableOnClick()
    }

    fun avatarUrlChange(avatarUrl: String) {
        _avatarUrlState.value = avatarUrl
        isEnableOnClick()
    }

    fun avatarUrlErrorStateChange(state: Boolean) {
        _avatarUrlErrorState.value = state
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

    fun birthdateChange(birthday: LocalDate?) {
        _birthdateState.value = birthday!!
        isEnableOnClick()
    }

    fun birthdateErrorStateChange(state: Boolean) {
        _birthdateErrorState.value = state
        isEnableOnClick()
    }

    fun genderMaleChanged(newValue: Boolean) {
        _genderMale.value = newValue
        genderChange("Male")
        isEnableOnClick()
    }

    fun genderFemaleChanged(newValue: Boolean) {
        _genderFemale.value = newValue
        genderChange("Female")
        isEnableOnClick()
    }

    private fun genderChange(gender: String) {
        _genderState.value =
            if (genderMale.value) "Male" else if (genderFemale.value) "Female" else gender
    }

    fun genderErrorStateChange(state: Boolean) {
        _genderErrorState.value = state
        isEnableOnClick()
    }

    fun loadProfile() = viewModelScope.launch {
        if (profileState.value !is ProfileState.Default)
            _profileStateDate.postValue(ProfileState.Loading)

        //screenState.postValue(Event.loading())
        _isProfileLoadingData.postValue(true)
        userRepository.getProfile(
            object : GetProfileCallback<ProfileDto> {
                override fun onSuccess(
                    id: String,
                    username: String,
                    email: String,
                    avatarLink: String?,
                    name: String,
                    birthday: String,
                    gender: Int
                ) {
                    _isProfileLoadingData.postValue(false)

                    userId = id
                    _loginState.value = username
                    _emailState.value = email
                    _avatarUrlState.value = avatarLink ?: ""
                    _nameState.value = name
                    _birthdateState.value = LocalDateTime.parse(
                        birthday
                    ).toLocalDate()
                    _genderState.value = if (gender == 0) "Male" else "Female"

                    if (_genderState.value == "Male") {
                        _genderMale.value = true
                        _genderFemale.value = false
                    } else {
                        _genderFemale.value = true
                        _genderMale.value = false
                    }

                    _profileStateDate.postValue(ProfileState.Default)

                }

                override fun onError(error: String?) {
                    _profileStateDate.postValue(ProfileState.UnknownError)

                    _isProfileLoadingData.postValue(false)
                    Log.d("MovieDetailsError", "$error")
                }

            })

    }

    fun onSaveClick() = viewModelScope.launch {
        birthdateState.value?.atStartOfDay()?.let {
            ProfileDto(
                id = userId,
                username = loginState.value,
                email = emailState.value,
                avatarLink = avatarUrlState.value,
                name = nameState.value,
                birthday = it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")),
                gender = if(genderState.value == "Male") 0 else 1,
            )
        }?.let {
            userRepository.updateProfile(
                it,
                _profileStateDate
            )
        }
    }

    fun onLogoutClick() = viewModelScope.launch {
        accountRepository.logout(_profileStateDate)
    }

    private fun isEnableOnClick() {
        _correctnessFields.value =
            !_avatarUrlErrorState.value && !_emailErrorState.value && !_nameErrorState.value && !_birthdateErrorState.value && !_genderErrorState.value
    }

}