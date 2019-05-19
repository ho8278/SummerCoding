package com.example.calendar.data

import android.content.Context
import com.example.calendar.data.local.db.DbHelper
import com.example.calendar.data.local.db.DbImpl
import com.example.calendar.data.local.pref.PreferenceHelper
import com.example.calendar.data.local.pref.PreferenceImpl
import com.example.calendar.data.model.Schedule
import io.reactivex.Single

class DataManager():DataSource{

    companion object {
        @Volatile
        private var INSTANCE : DataSource?=null
        private lateinit var dbHelper: DbHelper
        private lateinit var prefHelper:PreferenceHelper

        fun getInstance(context:Context):DataSource{
            return INSTANCE ?: DataManager().apply {
                dbHelper = DbImpl.getInstance(context)
                prefHelper = PreferenceImpl.getInstance(context)
                INSTANCE=this
            }
        }
    }

    override fun getInt(key: String): Int = prefHelper.getInt(key)

    override fun saveInt(key: String, item: Int) {
        prefHelper.saveInt(key,item)
    }

    override fun insertSchedule(schedule: Schedule) {
        dbHelper.insertSchedule(schedule)
    }

    override fun loadSchedule(): Single<List<Schedule>> {
        return dbHelper.loadSchedule()
    }
}