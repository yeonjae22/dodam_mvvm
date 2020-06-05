package com.yeonproject.dodam_mvvm.view.word

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import com.yeonproject.dodam_mvvm.data.model.Point

class WordPaint(context: Context?) : View(context) {
    private val points = mutableListOf<Point>()
    private var paintColor = Color.BLACK
    private val paint =
        Paint().apply {
            isAntiAlias = true
            strokeWidth = 10F
        }

    fun setColor(color: Int) {
        paintColor = color
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 1 until points.size) {
            paint.color = points[i].color
            if (!points[i].isDraw) continue
            canvas?.drawLine(
                points[i - 1].x,
                points[i - 1].y,
                points[i].x,
                points[i].y,
                paint
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    points.add(
                        Point(
                            event.x,
                            event.y,
                            true,
                            paintColor
                        )
                    )
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                }
                MotionEvent.ACTION_DOWN -> points.add(
                    Point(
                        event.x,
                        event.y,
                        false,
                        paintColor
                    )
                )
            }
        }
        return true
    }
}