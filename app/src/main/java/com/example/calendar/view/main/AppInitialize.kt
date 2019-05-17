package com.example.calendar.view.main

import android.app.Application
import com.example.calendar.data.DataManager
import com.example.calendar.data.DataSource

class AppInitialize:Application(){
    companion object {
        lateinit var dataSource:DataSource
    }

    override fun onCreate() {
        super.onCreate()
        dataSource =DataManager.getInstance(applicationContext)
    }
}