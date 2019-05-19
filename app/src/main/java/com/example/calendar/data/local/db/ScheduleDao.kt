package com.example.calendar.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calendar.data.model.Schedule
import io.reactivex.Single

@Dao
interface ScheduleDao{
    @Insert
    fun insertSchedule(schedule: Schedule)

    @Query("select * from schedule")
    fun loadSchedule(): Single<List<Schedule>>
}