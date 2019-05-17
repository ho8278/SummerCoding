package com.example.calendar.view.month

import com.example.calendar.view.LineSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class MonthDecorator(val dates:HashSet<CalendarDay>, val color:Int):DayViewDecorator{

    override fun shouldDecorate(day: CalendarDay?): Boolean = dates.contains(day)

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(DotSpan(8f,color))
    }

}