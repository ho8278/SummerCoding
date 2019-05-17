package com.example.calendar.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calendar.data.model.Schedule

@Database(entities = [ Schedule::class ],version = 1)
abstract class AppDataBase:RoomDatabase(){
    abstract val scheduleDao:ScheduleDao
}