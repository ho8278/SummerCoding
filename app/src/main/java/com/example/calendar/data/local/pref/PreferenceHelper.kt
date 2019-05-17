package com.example.calendar.data.local.pref

interface PreferenceHelper{
    fun getInt(key:String):Int
    fun saveInt(key:String, item:Int)
}