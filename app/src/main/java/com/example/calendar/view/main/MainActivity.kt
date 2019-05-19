package com.example.calendar.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calendar.R
import com.example.calendar.databinding.ActivityMainBinding
import com.example.calendar.view.date.DateFragment
import com.example.calendar.view.month.MonthFragment
import com.example.calendar.view.week.WeekFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName
    private val FIRST_TAB = 0
    private val SECOND_TAB = 1
    private val THIRD_TAB = 2
    lateinit var binding: ActivityMainBinding
    private val monthFragment = MonthFragment()
    private val weekFragment = WeekFragment()
    private val dateFragment = DateFragment()
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()

        viewModel.init()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1)
            monthFragment.refresh()
        else if (requestCode == 2)
            weekFragment.refresh()
        else
            dateFragment.refresh()
    }

    private fun initView() {
        viewModel =
            MainViewModel(AppInitialize.dataSource)
        binding.viewmodel = viewModel

        tl_tab_container.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.setPosition(tab?.position ?: 0)
                val transaction = supportFragmentManager.beginTransaction()
                when (tab?.position) {
                    FIRST_TAB -> {
                        transaction.apply {
                            replace(R.id.fragment_calendar, monthFragment)
                            commit()
                        }
                    }
                    SECOND_TAB -> {
                        transaction.apply {
                            replace(R.id.fragment_calendar, weekFragment)
                            commit()
                        }
                    }
                    THIRD_TAB -> {
                        transaction.apply {
                            replace(R.id.fragment_calendar, dateFragment)
                            commit()
                        }
                    }
                }
            }
        })

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_calendar, monthFragment)
            commit()
        }
    }

    override fun onStart() {
        Log.e(TAG, "onStart")
        super.onStart()
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
        super.onStop()
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        viewModel.destroy()
        super.onDestroy()
    }
}
