package ru.otus.animations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class TwoCircle(context: Context?) : View(context) {
    var paint1: Paint? = null
    var paint2: Paint? = null

    init {
        paint1 = Paint()
        paint2 = Paint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = width
        val y = height
        val radius = 100
        paint1!!.style = Paint.Style.FILL
        paint1!!.color = Color.WHITE
        canvas.drawPaint(paint1!!)
        paint1!!.color = Color.BLUE
        canvas.drawCircle((x / 3).toFloat(), (y / 2).toFloat(), radius.toFloat(), paint1!!)

        paint2!!.style = Paint.Style.FILL
        paint2!!.color = Color.WHITE
        canvas.drawPaint(paint2!!)
        paint2!!.color = Color.parseColor("#CD5C5C")
        canvas.drawCircle((2 * x / 3).toFloat(), (y / 2).toFloat(), radius.toFloat(), paint2!!)
    }
}
