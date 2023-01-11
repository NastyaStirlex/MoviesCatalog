package com.example.moviescatalog.screens.ProfileScreen.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.moviescatalog.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: MovieRepository)  : ViewModel() {
    private val _loginState = mutableStateOf("")
    private val _avatarUrlState = mutableStateOf("")
    private val _emailState = mutableStateOf("")
    private val _nameState = mutableStateOf("")
    private val _birthdayState = mutableStateOf<LocalDate>(LocalDate.MIN)
    private val _genderState = mutableStateOf(null)
    private val _canSaveState = mutableStateOf(false)
}