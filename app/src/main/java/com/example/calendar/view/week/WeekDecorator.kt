package com.example.calendar.view.week

import com.example.calendar.view.LineSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class WeekDecorator(val dates:Pair<CalendarDay,String>, val color:Int):DayViewDecorator{
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.first==day
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(LineSpan(dates.second, color))
    }
}