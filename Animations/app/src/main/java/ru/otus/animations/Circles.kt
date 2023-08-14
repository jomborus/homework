package ru.otus.animations

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.LinearInterpolator


class Circles(context: Context?) : View(context) {
    var paint: Paint? = null
    private var radius = 150
    private var pinkRadius = 150F
    private var alpha = 255
    private var priority = true

    private var blueX: Float = 1 / 3F
    private var pinkX: Float = 2 / 3F

    private val circleList = mutableListOf((63 to 400F), (127 to 300F), (191 to 200F), (255 to 100F))

    init {
        paint = Paint()
        startAnimationOne()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint!!.style = Paint.Style.FILL
        paint!!.color = Color.WHITE
        canvas.drawPaint(paint!!)
        if (priority) {
            paint!!.color = Color.parseColor("#FF1493")
            paint?.alpha = alpha
            canvas.drawCircle(pinkX * width, (height / 4).toFloat(), pinkRadius, paint!!)
            paint!!.color = Color.parseColor("#0000FF")
            paint?.alpha = 255
            canvas.drawCircle(blueX * width, (height / 4).toFloat(), radius.toFloat(), paint!!)
        } else {
            paint!!.color = Color.parseColor("#0000FF")
            canvas.drawCircle(blueX * width, (height / 4).toFloat(), radius.toFloat(), paint!!)
            paint!!.color = Color.parseColor("#FF1493")
            canvas.drawCircle(pinkX * width, (height / 4).toFloat(), pinkRadius, paint!!)
        }
        paint!!.color = Color.parseColor("#00FFFF")
        Log.d("Circle", "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")
        circleList.forEach {
            Log.d("Circle", "first:${it.first}second:${it.second}")
            paint?.alpha = it.first
            canvas.drawCircle(
                (width / 2).toFloat(),
                (3 * height / 4).toFloat(),
                it.second,
                paint!!
            )
        }
    }

    private fun startAnimationOne() {
        ValueAnimator.ofFloat(0F, 2F).apply {
            duration = 2000
            interpolator = AccelerateInterpolator()
            addUpdateListener {
                if (it.animatedValue as Float <= 1) {
                    blueX = (1 + it.animatedValue as Float) / 3F
                    pinkX = (2 - it.animatedValue as Float) / 3F
                    pinkRadius =
                        radius.toFloat() * kotlin.math.abs(2 * (0.5F - it.animatedValue as Float))
                    alpha = (255 * kotlin.math.abs(2 * (0.5F - it.animatedValue as Float))).toInt()
                    priority = true
                } else {
                    blueX = (3 - it.animatedValue as Float) / 3F
                    pinkX = (0 + it.animatedValue as Float) / 3F
                    priority = false
                }
                invalidate()
            }
            repeatCount = Animation.INFINITE
            start()
        }
        ValueAnimator.ofFloat(0F, 1F).apply {
            duration = 5000
            interpolator = LinearInterpolator()
            addUpdateListener {
                (0..3).forEach { index ->
                    circleList[index] = (index * 64 + (64 *(1 - it.animatedValue as Float)).toInt()) to ((4 - index) * 100F + 100F * it.animatedValue as Float)
                }
                invalidate()
            }
            repeatCount = Animation.INFINITE
            start()
        }
    }
}
