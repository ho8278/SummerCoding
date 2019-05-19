package com.example.calendar.view.week

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.calendar.R
import com.example.calendar.databinding.FragmentWeekCalendarBinding
import com.example.calendar.view.SelectionDecorator
import com.example.calendar.view.addschedule.AddSchedule
import com.example.calendar.view.main.AppInitialize
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_week_calendar.*

class WeekFragment: Fragment(){


    val TAG = WeekFragment::class.java.simpleName
    lateinit var binding : FragmentWeekCalendarBinding
    lateinit var viewModel: WeekFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG,"onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e(TAG,"onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week_calendar,container,false)
        binding.mcvWeek.apply {
            setSelectedDate(CalendarDay.today())
            addDecorators(SelectionDecorator(activity!!.getDrawable(R.drawable.selector_decorator)))
        }
        viewModel= WeekFragmentViewModel(AppInitialize.dataSource)
        binding.viewmodel=viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e(TAG,"onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        fab_week_add_schedule.setOnClickListener {
            val intent = Intent(context, AddSchedule::class.java)
            val current = mcv_week.selectedDate
            intent.putExtra("date","${current?.year}-${current?.month}-${current?.day}")
            activity?.startActivityForResult(intent,2)
        }
    }

    fun refresh(){
        viewModel.init()
    }

    override fun onResume() {
        Log.e(TAG,"onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.e(TAG,"onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e(TAG,"onStop")
        //viewModel.destroy()
        super.onStop()
    }

    override fun onDetach() {
        Log.e(TAG,"onDetach")
        super.onDetach()
    }
}