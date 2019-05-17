package com.example.calendar.data

import com.example.calendar.data.model.Schedule
import io.reactivex.Single

interface DataSource{
    fun getInt(key:String):Int
    fun saveInt(key:String, item:Int)
    fun insertSchedule(schedule: Schedule)
    fun loadSchedule(date:String): Single<List<Schedule>>
    fun loadSchedule(): Single<List<Schedule>>
}