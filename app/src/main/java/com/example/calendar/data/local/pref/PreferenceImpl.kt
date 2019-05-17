package com.example.calendar.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferenceImpl:PreferenceHelper{


    companion object {
        val LAST_POSITION = "LAST_POSITION"

        @Volatile
        private var INSTANCE:PreferenceHelper?=null
        private lateinit var preference:SharedPreferences

        fun getInstance(context:Context): PreferenceHelper{
            return INSTANCE ?: PreferenceImpl().apply {
                preference = PreferenceManager.getDefaultSharedPreferences(context)
                INSTANCE=this
            }
        }
    }

    override fun getInt(key: String): Int = preference.getInt(key,0)

    override fun saveInt(key: String, item: Int) {
        preference.edit().apply {
            putInt(key,item)
            apply()
        }
    }
}