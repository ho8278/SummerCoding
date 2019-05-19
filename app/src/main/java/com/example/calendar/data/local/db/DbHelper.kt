package com.example.calendar.data.local.db

import com.example.calendar.data.model.Schedule
import io.reactivex.Single

interface DbHelper{
    fun insertSchedule(schedule: Schedule)
    fun loadSchedule(): Single<List<Schedule>>
}