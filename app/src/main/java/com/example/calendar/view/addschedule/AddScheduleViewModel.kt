package com.example.calendar.view.addschedule

import androidx.databinding.ObservableField
import com.example.calendar.data.DataSource
import com.example.calendar.data.model.Schedule
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class AddScheduleViewModel(val dataSource: DataSource, val date: String, val saveResult:SaveResult) {
    val title = ObservableField<String>("")

    fun saveSchedule(){
        if(title.get()?.length==0)
            saveResult.showToast("일정 내용이 없습니다.")
        else{
            dataSource.insertSchedule(Schedule(UUID.randomUUID().toString(),title.get() ?: "" ,date))
            saveResult.showToast("저장 완료")
        }
    }
}