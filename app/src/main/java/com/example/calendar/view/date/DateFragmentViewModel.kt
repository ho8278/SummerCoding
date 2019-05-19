package com.example.calendar.view.date

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.example.calendar.data.DataSource
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*

class DateFragmentViewModel(val dataSource:DataSource){
    val TAG = DateFragmentViewModel::class.java.simpleName
    val disposable= CompositeDisposable()
    val dateList = ObservableArrayList<CalendarDay>()
    val eventList = ObservableField<HashMap<CalendarDay,String>>()
    val yearmonth=ObservableField<String>()

    init {
        for(i in -300..300){
            val currentDay = Calendar.getInstance()
            currentDay.add(Calendar.DAY_OF_YEAR,i)
            dateList.add(CalendarDay.from(currentDay.get(Calendar.YEAR),currentDay.get(Calendar.MONTH)+1,currentDay.get(Calendar.DATE)))
        }
    }

    fun init(){
        disposable.add(
            dataSource.loadSchedule()
                .map { list->
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
                    eventList.set(hashMap)
                },{
                    Log.e(TAG,it.message)
                })
        )
    }

    fun destroy(){
        disposable.clear()
    }

    fun setYearMonth(position :Int ){
        yearmonth.set("${dateList[position].year}년 ${dateList[position].month}월")
    }
}