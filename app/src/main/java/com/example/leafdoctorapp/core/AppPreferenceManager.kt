package com.example.leafdoctorapp.core

import android.content.Context
import android.content.SharedPreferences
import com.example.leafdoctorapp.BuildConfig


interface AppPreferenceManager {

    var accessToken: String?
    fun saveAccessToken(token: String)


    var refreshToken: String?

    var fullName : String?
    fun saveRefreshToken(token: String)

    var username : String?

    var appFirstLaunch: Boolean

    fun resetToken()

}

class AppPreferenceManagerImpl(context: Context) :
    AppPreferenceManager {

    override var appFirstLaunch: Boolean
        get() = getBoolean(appFirstLaunchKey)
        set(value) {
            editor.putBoolean(appFirstLaunchKey, value).apply()
        }

    override var refreshToken: String?
        get() = getString(refreshTokenKey)
        set(token) {
            editor.putString(refreshTokenKey, token).apply()
        }
    override var fullName: String?
        get() = getString(fullNameKey)
        set(fullname) {
            editor.putString(fullNameKey, fullname).apply()
        }

    override var accessToken: String?
        get() = getString(accessTokenKey)
        set(token) {
            editor.putString(accessTokenKey, "Bearer $token").apply()
        }


    override fun saveRefreshToken(token: String) {
        refreshToken = token
    }

    override var username: String?
        get() = getString(userNameKey)
        set(username) {
            editor.putString(userNameKey, username).apply()
        }

    override fun saveAccessToken(token: String) {
        accessToken = token
    }


    /***
     * BASE IMPLEMENTATION
     */

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            sharedPreferencesKey,
            Context.MODE_PRIVATE
        )

    private val editor = sharedPreferences.edit()

    override fun resetToken() {
        editor.remove(accessTokenKey).apply()
        editor.remove(refreshTokenKey).apply()
    }


    private fun getString(key: String): String? {
        sharedPreferences.getString(key, "").let { s ->
            return if (s.isNullOrBlank())
                null
            else
                s
        }
    }

    private fun getInt(key: String): Int {
        sharedPreferences.getInt(key, 0).let { s ->
            return s
        }
    }

    private fun getBoolean(key: String): Boolean {
        sharedPreferences.getBoolean(key, true).let {
            return it
        }
    }


    companion object {

        private const val sharedPreferencesKey = "USERDATA"
        private const val accessTokenKey = "API_TOKEN"
        private const val refreshTokenKey = "REFRESH_API_TOKEN"
        private const val appFirstLaunchKey = "APP_FIRST_LAUNCH"
        private const val fullNameKey = "FULL_NAME_KEY"
        private const val userNameKey = "USER_NAME_KEY"
        private const val baseUrl = BuildConfig.BUILD_TYPE
    }
}