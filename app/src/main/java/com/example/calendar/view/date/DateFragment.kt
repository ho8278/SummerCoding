package com.example.calendar.view.date

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.calendar.R
import com.example.calendar.databinding.FragmentDateCalendarBinding
import com.example.calendar.databinding.FragmentMonthCalendarBinding
import com.example.calendar.view.SelectionDecorator
import com.example.calendar.view.addschedule.AddSchedule
import com.example.calendar.view.main.AppInitialize
import com.example.calendar.view.month.MonthFragment
import com.example.calendar.view.month.MonthFragmentViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.fragment_date_calendar.*
import kotlinx.android.synthetic.main.fragment_week_calendar.*

class DateFragment : Fragment() {

    val TAG = DateFragment::class.java.simpleName
    lateinit var binding: FragmentDateCalendarBinding
    lateinit var viewModel: DateFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e(TAG, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date_calendar, container, false)

        viewModel = DateFragmentViewModel(AppInitialize.dataSource)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()

        vp_date_calendar.apply {
            adapter = ViewPagerAdapter()
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    viewModel.setYearMonth(position)
                }
            })
            pageMargin = 120
            setPageTransformer(false,
                ViewPagerAnimator(0.8f)
            )
        }

        iv_prev.setOnClickListener {
            val currentPosition = vp_date_calendar.currentItem
            if (currentPosition != 0)
                vp_date_calendar.currentItem = currentPosition - 1
        }
        iv_next.setOnClickListener {
            val currentPosition = vp_date_calendar.currentItem
            if (currentPosition != (vp_date_calendar.adapter?.count ?: 0) - 1)
                vp_date_calendar.currentItem = currentPosition + 1
        }

        fab_date_add_schedule.setOnClickListener {
            val intent = Intent(context, AddSchedule::class.java)
            val current = (vp_date_calendar.adapter as ViewPagerAdapter).getDate(vp_date_calendar.currentItem)
            intent.putExtra("date","${current?.year}-${current?.month}-${current?.day}")
            activity?.startActivityForResult(intent,3)
        }
    }

    fun refresh(){
        viewModel.init()
    }

    override fun onResume() {
        Log.e(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.e(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e(TAG, "onStop")
        viewModel.destroy()
        super.onStop()
    }

    override fun onDetach() {
        Log.e(TAG, "onDetach")
        super.onDetach()
    }

    class ViewPagerAnimator(val smallerScale: Float) : ViewPager.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val absPosition = Math.abs(position)
            if (absPosition >= 1) {
                page.scaleY = smallerScale
            } else {
                page.scaleY = (smallerScale - 1) * absPosition + 1
            }
        }
    }
}