package com.example.conectskills.data

import android.content.Context
import androidx.core.content.edit

class AuthManager(private val context: Context) {
    companion object {
        private const val PREFS_NAME = "auth_prefs"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE = "phone"
        private const val KEY_PROFILE_IMAGE = "profile_image"
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

    fun saveUserData(
        name: String,
        email: String,
        password: String,
        phone: String? = null,
        profileImageUri: String? = null
    ) {
        sharedPreferences.edit {
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
            putString(KEY_PHONE, phone)
            putString(KEY_PROFILE_IMAGE, profileImageUri)
        }
    }

    fun getUserData(): Map<String, String?> = mapOf(
        "name" to sharedPreferences.getString(KEY_NAME, ""),
        "email" to sharedPreferences.getString(KEY_EMAIL, ""),
        "phone" to sharedPreferences.getString(KEY_PHONE, null),
        "profileImage" to sharedPreferences.getString(KEY_PROFILE_IMAGE, null)
    )
}