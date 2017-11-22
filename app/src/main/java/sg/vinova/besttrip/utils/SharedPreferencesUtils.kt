package sg.vinova.besttrip.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import sg.vinova.besttrip.library.Constant


/**
 * Created by Hanah on 11/22/2017.
 */
class SharedPreferencesUtils(private var context: Context) {

    companion object {
        fun newInstance(context: Context): SharedPreferencesUtils = SharedPreferencesUtils(context)
    }

    init {
        context.getSharedPreferences("BestTrip", Context.MODE_PRIVATE)
    }

    fun getStringPreference(key: String): String? {
        var value: String? = null
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null) {
            value = preferences.getString(key, null)
        }
        return value
    }

    fun setStringPreference(key: String, value: String): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null && !TextUtils.isEmpty(key)) {
            val editor = preferences.edit()
            editor.putString(key, value)
            return editor.commit()
        }
        return false
    }

    fun getToken(): String? {
        var value: String? = null
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null) {
            value = preferences.getString(Constant.TokenKey, null)
        }
        return value
    }

    fun setToken(value: String): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putString(Constant.TokenKey, value)
            return editor.commit()
        }
        return false
    }
}