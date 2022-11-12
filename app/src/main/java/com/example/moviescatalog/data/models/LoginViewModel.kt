package com.example.moviescatalog.data.models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalog.data.di.ApiClient
import com.example.moviescatalog.data.authapi.authorization.LoginBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val isSuccessLoading = mutableStateOf(false)
    val progressBar = mutableStateOf(false)
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun onLoginPressed(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressBar.value = true
                val apiService = ApiClient.getApiService("https://react-midterm.kreosoft.space/")
                val responseService = apiService.login(LoginBody(username = login, password = password))

                if (responseService.isSuccessful) {
                    //if(responseService.code() == 400)
                    delay(1500L)
                    isSuccessLoading.value = true
                    responseService.body()?.let { accessToken ->
                        Log.d("Logging", "Response Token: ${accessToken.loginToken}")
                    }
                } else {
                    Log.d("Code: ", responseService.code().toString())
                    isSuccessLoading.value = false
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)
                progressBar.value = false
            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
                progressBar.value = false
            }
        }
    }
}