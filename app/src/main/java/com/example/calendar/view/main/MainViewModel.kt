package com.example.calendar.view.main

import android.util.Log
import androidx.databinding.ObservableInt
import com.example.calendar.data.DataSource
import com.example.calendar.data.local.pref.PreferenceImpl
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(val dataSource: DataSource) {
    val lastPosition = ObservableInt()
    val disposable = CompositeDisposable()


    fun init() {
        dataSource.getInt(PreferenceImpl.LAST_POSITION).apply {
            Log.e("MainViewModel",this.toString())
            lastPosition.set(this)
        }
    }

    fun setPosition(position:Int){
        lastPosition.set(position)
    }

    fun destroy() {
        dataSource.saveInt(PreferenceImpl.LAST_POSITION, lastPosition.get())
        disposable.clear()
    }
}