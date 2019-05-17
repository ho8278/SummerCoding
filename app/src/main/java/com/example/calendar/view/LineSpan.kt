package com.example.calendar.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.LineBackgroundSpan
import android.util.Log

class LineSpan(val text: String, val color: Int) : LineBackgroundSpan {


    override fun drawBackground(
        c: Canvas?,
        p: Paint?,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence?,
        start: Int,
        end: Int,
        lnum: Int
    ) {
        Log.e("TEST","$left $right $top $bottom")
        val oldColor = p?.color ?: 0
        if (oldColor != 0)
            p?.color = color
        val textPaint = TextPaint()
        textPaint.apply {
            color=Color.WHITE
            style=Paint.Style.FILL
            textSize=30f
            textAlign=Paint.Align.LEFT
            isLinearText=true
        }
        val rect = Rect(left+10, top+bottom, right-10, bottom+50)
        val txt= TextUtils.ellipsize(this.text,textPaint,(rect.width()-10).toFloat(),TextUtils.TruncateAt.END)
        c?.drawRect(rect, p)
        c?.drawText(txt,0,txt.length,rect.left.toFloat(),rect.bottom-10f,textPaint)
        p?.color = oldColor
    }
}