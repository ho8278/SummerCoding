package com.example.calendar.view.date

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.calendar.R
import com.example.calendar.databinding.ItemDateBinding
import com.prolificinteractive.materialcalendarview.CalendarDay

class ViewPagerAdapter:PagerAdapter(){

    val dateList =  mutableListOf<CalendarDay>()

    val dates = HashMap<CalendarDay,String>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getCount(): Int {
        return dateList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<ItemDateBinding>(LayoutInflater.from(container.context), R.layout.item_date,container,false)
        val event = dates[dateList[position]] ?: ""
        binding.viewmodel = DateViewModel(dateList[position],event)
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun setList(list: MutableList<CalendarDay>){
        dateList.clear()
        dateList.addAll(list)
        notifyDataSetChanged()
    }

    fun setEvents(events:HashMap<CalendarDay,String>){
        dates.clear()
        dates.putAll(events)
        notifyDataSetChanged()
    }

    fun getDate(position:Int):CalendarDay{
        return dateList[position]
    }
}