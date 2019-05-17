package com.example.calendar.view.date

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.prolificinteractive.materialcalendarview.CalendarDay

class DateViewModel(){
    constructor(currentDay:CalendarDay):this(){
        date.set("${currentDay.day}")
    }
    constructor(currentDay: CalendarDay,event:String):this(currentDay){
        if(event.length!=0){
            isEvent.set(true)
            this.event.set(event)
        }
    }
    val date = ObservableField<String>("")
    val isEvent = ObservableBoolean(false)
    val event = ObservableField<String>("")
}