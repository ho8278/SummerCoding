package com.example.calendar.view.month

import android.util.Log
import androidx.databinding.ObservableField
import com.example.calendar.data.DataSource
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*

class MonthFragmentViewModel(val dataSource: DataSource){
    val TAG = MonthFragmentViewModel::class.java.simpleName
    val dates = ObservableField<HashSet<CalendarDay>>()
    val disposable= CompositeDisposable()

    fun init(){
        disposable.add(
            dataSource.loadSchedule()
                .map {list->
                    val hashSet = HashSet<CalendarDay>()
                    list.forEach {
                        val formatter = SimpleDateFormat("yyyy-MM-dd")
                        val date = formatter.parse(it.date)
                        val calendar = Calendar.getInstance()
                        calendar.time=date
                        hashSet.add(CalendarDay.from(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH)))
                    }
                    hashSet
                }
                .subscribe({ hashSet ->
                    Log.e(TAG,hashSet.toString())
                    dates.set(hashSet)
                },{
                    Log.e(TAG,it.message)
                })
        )
    }

    fun destroy(){
        disposable.clear()
    }
}