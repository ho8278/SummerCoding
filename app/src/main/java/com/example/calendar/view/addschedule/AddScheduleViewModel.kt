package com.example.calendar.view.addschedule

import androidx.databinding.ObservableField
import com.example.calendar.data.DataSource
import com.example.calendar.data.model.Schedule
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class AddScheduleViewModel(val dataSource: DataSource, val date: String) {
    val title = ObservableField<String>()

    fun saveSchedule(){
        dataSource.insertSchedule(Schedule(UUID.randomUUID().toString(),title.get() ?: "" ,date))
    }
}