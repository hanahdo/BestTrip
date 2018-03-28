package sg.vinova.besttrip.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import sg.vinova.besttrip.library.Constant



class SharedPreferencesUtils() {
    private lateinit var context: Context

    companion object {
        fun getInstance(context: Context): SharedPreferencesUtils {
            val sharePreferenceUtils = SharedPreferencesUtils()
            sharePreferenceUtils.context = context
            return sharePreferenceUtils
        }
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

    fun setStringPreference(key: String, value: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences != null && !TextUtils.isEmpty(key)) {
            preferences.edit().putString(key, value).apply()
        }
    }
}
