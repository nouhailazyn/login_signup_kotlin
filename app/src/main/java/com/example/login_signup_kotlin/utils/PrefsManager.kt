package com.example.login_signup_kotlin.utils

import android.content.Context
import androidx.core.content.edit

class PrefsManager(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = prefs.getBoolean("is_logged_in", false)
        set(value) = prefs.edit { putBoolean("is_logged_in", value) }

    var userEmail: String?
        get() = prefs.getString("user_email", null)
        set(value) = prefs.edit { putString("user_email", value) }
}
