package com.example.calendar.util

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.example.calendar.R
import com.example.calendar.view.date.ViewPagerAdapter
import com.example.calendar.view.month.MonthDecorator
import com.example.calendar.view.week.WeekDecorator
import com.google.android.material.tabs.TabLayout
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

object BindingUtils {
    @JvmStatic
    @BindingAdapter("selectTab")
    fun selectTab(view: TabLayout, item: Int?) {
        Log.e("BindingAdapter", item.toString())
        val tab = view.getTabAt(item ?: 0)
        view.selectTab(tab)
    }

    @JvmStatic
    @BindingAdapter("monthEvent")
    fun addMonthDecorator(view: MaterialCalendarView, itemSet: HashSet<CalendarDay>?) {
        Log.e("BindingAdapter", itemSet.toString())
        val color = ContextCompat.getColor(view.context, R.color.colorEvent)
        view.addDecorator(MonthDecorator(itemSet ?: HashSet(), color))
    }

    @JvmStatic
    @BindingAdapter("weekEvent")
    fun addWeekDecorator(view: MaterialCalendarView, itemSet: HashMap<CalendarDay, String>?) {
        Log.e("BindingAdapter", itemSet.toString())
        val color = ContextCompat.getColor(view.context, R.color.colorEvent)
        for (key in itemSet?.keys ?: HashSet())
            view.addDecorator(WeekDecorator(key to (itemSet?.get(key) ?: ""), color))
    }

    @JvmStatic
    @BindingAdapter("dateEvent")
    fun addDateEvents(view: ViewPager, item: HashMap<CalendarDay, String>?) {
        val adapter = view.adapter as ViewPagerAdapter
        adapter.setEvents(item ?: HashMap())
    }

    @JvmStatic
    @BindingAdapter("dateList")
    fun addDateList(view: ViewPager, item: MutableList<CalendarDay>?) {
        val currentDay = Calendar.getInstance()
        val position = item?.indexOf(CalendarDay.from(currentDay.get(Calendar.YEAR),currentDay.get(Calendar.MONTH)+1,currentDay.get(Calendar.DAY_OF_MONTH)))
        val adapter = view.adapter as ViewPagerAdapter
        adapter.setList(item ?: mutableListOf())
        view.currentItem = position ?:0
    }
}