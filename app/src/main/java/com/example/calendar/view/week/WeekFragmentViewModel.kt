package com.example.calendar.view.week

import android.util.Log
import androidx.databinding.ObservableField
import com.example.calendar.data.DataSource
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*

class WeekFragmentViewModel(val dataSource:DataSource){
    val TAG = WeekFragmentViewModel::class.java.simpleName
    val dates = ObservableField<HashMap<CalendarDay,String>>()
    val disposable= CompositeDisposable()

    fun init(){
        disposable.add(
            dataSource.loadSchedule()
                .map { list ->
                    val hashMap = HashMap<CalendarDay, String>()

                    list.forEach {
                        val formatter = SimpleDateFormat("yyyy-MM-dd")
                        val date = formatter.parse(it.date)
                        val calendar = Calendar.getInstance()
                        calendar.time = date
                        hashMap.put(
                            CalendarDay.from(
                                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(
                                    Calendar.DAY_OF_MONTH
                                )
                            ), it.title
                        )
                    }
                    hashMap
                }.subscribe({ hashMap ->
                    Log.e(TAG,hashMap.toString())
                    dates.set(hashMap)
                },{
                    Log.e(TAG,it.message)
                })
        )
    }

    fun destroy(){
        disposable.clear()
    }
}