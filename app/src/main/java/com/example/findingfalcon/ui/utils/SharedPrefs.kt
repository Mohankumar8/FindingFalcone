package com.example.findingfalcon.ui.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    companion object {
        private const val PREF = "FalconePreferences"
        private const val PREF_TOKEN = "token"
    }

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun getToken(): String? {
        return sharedPrefs.getString(PREF_TOKEN, "")
    }

    fun saveToken(token: String) {
        val editor = sharedPrefs.edit()
        editor.putString(PREF_TOKEN, token)
            .apply()
    }

    fun clear() {
        sharedPrefs.edit().run {
            remove(PREF_TOKEN)
        }.apply()
    }

}