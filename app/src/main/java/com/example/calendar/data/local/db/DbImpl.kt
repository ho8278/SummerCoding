package com.example.calendar.data.local.db

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.calendar.data.model.Schedule
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DbImpl:DbHelper{
    companion object {
        @Volatile
        private var INSTANCE:DbHelper? = null
        private lateinit var appDataBase: AppDataBase

        fun getInstance(context:Context):DbHelper{
            return INSTANCE ?: DbImpl().apply {
                appDataBase = Room.databaseBuilder(context,AppDataBase::class.java,"ScheduleDataBase").build()
                INSTANCE=this
            }
        }
    }

    override fun insertSchedule(schedule: Schedule) {
        Completable.fromAction {
            appDataBase.scheduleDao.insertSchedule(schedule)
        }.subscribeOn(Schedulers.io())
            .doOnError { Log.e("DbImpl",it.message) }
            .subscribe()
    }

    override fun loadSchedule(): Single<List<Schedule>> {
        return appDataBase.scheduleDao.loadSchedule()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}