package com.example.kashishmalhotra.mycanvas

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_pie.*
import android.view.Display

class PieActivity : AppCompatActivity() {

    private var startAngle = 0f
    private var sweepAngle = 10f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie)
        init()
    }

    private fun init() {
        val textPaint = Paint()
        textPaint.color = ContextCompat.getColor(this, R.color.white)
        textPaint.textSize = 36f
        val display: Display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width = point.x
        val height = point.y

        val paint = Paint()
        val range = RectF(0f, 10f, width.toFloat(),
                width.toFloat())
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        ivPie.setImageBitmap(bitmap)
        val canvas = Canvas(bitmap)

        for (i in 1..36) {
            if (i % 2 == 0)
                paint.color = ContextCompat.getColor(this, R.color.colorPrimaryDark)
            else
                paint.color = ContextCompat.getColor(this, R.color.colorPrimary)
            canvas.drawArc(range, startAngle, sweepAngle, true, paint)


            val path = Path()
            path.addArc(range, startAngle, sweepAngle)
            val text = i.toString()
            val textWidth = paint.measureText(text)

            val horizontalOffset = (((2 * 3.14 * (width / 2)) / 36) - textWidth) / 2
            val verticalOffset = width / 10

            canvas.drawTextOnPath(text, path, horizontalOffset.toFloat() - 10, verticalOffset.toFloat(), textPaint)

            startAngle += sweepAngle
        }

    }
}
