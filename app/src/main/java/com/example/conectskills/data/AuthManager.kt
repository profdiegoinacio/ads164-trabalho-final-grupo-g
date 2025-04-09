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
        private const val KEY_CEP = "cep"
        private const val KEY_CPF = "cpf"
        private const val KEY_HABILIDADES = "habilidades"
        private const val KEY_PROFILE_IMAGE = "profile_image"
        private const val KEY_PROFILE_IMAGE_URI = "profile_image_uri"
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
        cep: String,
        habilidades: String? = null,
        cpf:String? = null,
        profileImageUri: String? = null
    ) {
        sharedPreferences.edit {
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
            putString(KEY_PHONE, phone)
            putString(KEY_CEP, cep)
            putString(KEY_CPF, cpf)
            putString(KEY_HABILIDADES, habilidades)
            putString(KEY_PROFILE_IMAGE, profileImageUri)
        }
    }

    fun getUserData(): Map<String, String?> = mapOf(
        "name" to sharedPreferences.getString(KEY_NAME, ""),
        "email" to sharedPreferences.getString(KEY_EMAIL, ""),
        "phone" to sharedPreferences.getString(KEY_PHONE, null),
        "cep" to sharedPreferences.getString(KEY_CEP, null),
        "cpf" to sharedPreferences.getString(KEY_CPF, null),
        "habilidades" to sharedPreferences.getString(KEY_HABILIDADES, null),
        "profileImage" to sharedPreferences.getString(KEY_PROFILE_IMAGE, null)
    )

    fun saveProfileImageUri(uri: String?) {
        sharedPreferences.edit {
            putString(KEY_PROFILE_IMAGE_URI, uri)
        }
    }

    fun getProfileImageUri(): String? {
        return sharedPreferences.getString(KEY_PROFILE_IMAGE_URI, null)
    }
}