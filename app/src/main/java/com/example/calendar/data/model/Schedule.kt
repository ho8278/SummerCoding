package com.example.calendar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(@PrimaryKey
                    val scheduleID:String,
                    val title:String,
                    val date:String)