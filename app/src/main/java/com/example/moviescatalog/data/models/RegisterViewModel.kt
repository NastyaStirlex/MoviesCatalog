package com.example.moviescatalog.data.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviescatalog.data.authapi.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository): ViewModel() {


}