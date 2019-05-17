package com.example.calendar.view.addschedule

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calendar.R
import com.example.calendar.databinding.ActivityAddScheduleBinding
import com.example.calendar.view.main.AppInitialize
import kotlinx.android.synthetic.main.activity_add_schedule.*

class AddSchedule:AppCompatActivity(){

    private val TAG = AddSchedule::class.java.simpleName
    private lateinit var binding: ActivityAddScheduleBinding
    private lateinit var viewModel:AddScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG,"onCreate")
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_schedule)

        initView()
        val data = intent.getStringExtra("date")
        viewModel = AddScheduleViewModel(AppInitialize.dataSource,data)
        binding.viewmodel = viewModel

    }

    private fun initView(){
        tv_save.setOnClickListener {
            viewModel.saveSchedule()
            finish()
        }
        tv_cancel.setOnClickListener {
            finish()
        }

    }

    override fun onStart() {
        Log.e(TAG,"onStart")
        super.onStart()
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
        super.onStop()
    }

    override fun onDestroy() {
        Log.e(TAG,"onDestroy")
        super.onDestroy()
    }

}