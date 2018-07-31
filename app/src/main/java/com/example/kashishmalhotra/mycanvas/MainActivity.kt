package com.example.kashishmalhotra.mycanvas

import android.content.Intent
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val OFFSET = 80
    private var offset = OFFSET
    private val multiplier = 100
    private lateinit var canvas: Canvas
    private val paint = Paint()
    private val paintText = Paint(Paint.UNDERLINE_TEXT_FLAG)
    private lateinit var bitmap: Bitmap
    private val rect = Rect()
    private val bounds = Rect()

    private var colorRectangle = 0
    private var colorBackground = 0
    private var colorAccent = 0
    private var colorText = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorRectangle = ContextCompat.getColor(this, R.color.colorRectangle)
        colorAccent = ContextCompat.getColor(this, R.color.colorAccent)
        colorBackground = ContextCompat.getColor(this, R.color.colorBackground)
        colorText = ContextCompat.getColor(this, R.color.colorPrimaryDark)

        paintText.color = colorText
        paintText.textSize = 40f

        paint.color = colorBackground

    }

    fun drawSomething(view: View) {
        val height = view.height
        val width = view.width
        val halfHeight = view.height / 2
        val halfWidth = view.width / 2

        if (offset == OFFSET) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            ivCanvas.setImageBitmap(bitmap)
            canvas = Canvas(bitmap)
            canvas.drawColor(colorBackground)

            canvas.drawText(getString(R.string.keep_tapping), 100f, 100f, paintText)
            offset += OFFSET
        } else {
            if (offset < halfHeight && offset < halfWidth) {
                paint.color = colorRectangle - offset * multiplier
                rect.set(offset, offset, width - offset, height - offset)
                canvas.drawRect(rect, paint)
                offset += OFFSET
            } else {
                paint.color = colorAccent
                canvas.drawCircle(halfWidth.toFloat(), halfHeight.toFloat(), (halfWidth / 5).toFloat(), paint)

                val text = getString(R.string.done)
                paintText.getTextBounds(text, 0, text.count(), bounds)
                val x = halfWidth.toFloat() - bounds.centerX()
                val y = halfHeight.toFloat() - bounds.centerY()

                canvas.drawText(text, x, y, paintText)
            }
        }
        view.invalidate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(getString(R.string.open_pie_activity))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, PieActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}
