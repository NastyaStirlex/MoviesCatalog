package com.example.moviescatalog.data.repository

import android.content.Context
import javax.inject.Inject

class JwtRepository @Inject constructor(){

    fun saveToken(context: Context, token: String) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("token", token)
            apply()
        }
    }

    fun getToken(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("token", null)
    }

    fun deleteToken(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("token", null)
            apply()
        }
    }
}