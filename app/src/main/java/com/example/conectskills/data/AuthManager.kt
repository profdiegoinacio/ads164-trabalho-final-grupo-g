package com.example.conectskills.data

import android.content.Context
import androidx.core.content.edit

class AuthManager(private val context: Context) {
    companion object {
        private const val PREFS_NAME = "auth_prefs"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserCredentials(email: String, password: String) {
        sharedPreferences.edit {
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
        }
    }

    fun getSavedEmail(): String? = sharedPreferences.getString(KEY_EMAIL, null)
    fun getSavedPassword(): String? = sharedPreferences.getString(KEY_PASSWORD, null)
}