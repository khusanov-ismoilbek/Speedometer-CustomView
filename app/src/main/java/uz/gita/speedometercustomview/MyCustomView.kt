package uz.gita.speedometercustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import uz.gita.speedometercustomview.utill.toRad
import kotlin.math.cos
import kotlin.math.sin

class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {
    private val paint = Paint()
    private var radiusMain = 0f
    private var radiusInner = 0f
    private var currentSpeed = 0
    private var speedLineValue = 0
    private var angleIndicator = 50f.toRad

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mainArc(canvas)
        innerArc(canvas)
        smallLine(canvas)
        bigLine(canvas)
        speedCenter(canvas)
        setSpeedTextNumber(canvas)
        speedOuterLine(canvas)
        speedIndicator(canvas)
    }

//    fun speedListener(speed: Int): Flow<Unit> = flow {
//        currentSpeed = speed
//        speedLineValue = speed
//        invalidate()
//        emit(Unit)
//    }.flowOn(Dispatchers.IO)

    fun speedListener(speed: Int) {
        angleIndicator = (speed * 13 / 11 + 50).toFloat().toRad
        currentSpeed = (speed)
        speedLineValue = (speed * 13 / 11)
        invalidate()
    }

    private fun mainArc(canvas: Canvas) {
        radiusMain = width / 2f - 50
        val x = width / 2f
        val y = height / 2f

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f
        paint.color = Color.parseColor("#252525")

        canvas.drawArc(
            x - radiusMain,
            y - radiusMain,
            x + radiusMain,
            y + radiusMain,
            140f,
            260f,
            false,
            paint
        )
    }

    private fun innerArc(canvas: Canvas) {
        radiusInner = width / 4f - 50
        val x = width / 2f
        val y = height / 2f
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        paint.color = Color.RED

        canvas.drawArc(
            x - radiusInner,
            y - radiusInner,
            x + radiusInner,
            y + radiusInner,
            140f,
            260f,
            false,
            paint
        )
    }

    private fun smallLine(canvas: Canvas) {
        radiusMain = width / 2f - 120

        val x = width / 2f
        val y = height / 2f
        val length = 30
        var angle: Float = 50f.toRad

        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED

        var xStart: Float
        var yStart: Float
        var xStop: Float
        var yStop: Float

        for (i in 0..110) {
            xStart = x - radiusMain * sin(angle)
            yStart = y + radiusMain * cos(angle)
            xStop = xStart + length * sin(angle)
            yStop = yStart - length * cos(angle)

            canvas.drawLine(
                xStart,
                yStart,
                xStop,
                yStop,
                paint
            )
            angle += 2.37f.toRad
        }
    }

    private fun bigLine(canvas: Canvas) {
        radiusMain = width / 2f - 120

        val x = width / 2f
        val y = height / 2f
        val length = 50
        var angle: Float = 50f.toRad

        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLUE

        var xStart: Float
        var yStart: Float
        var xStop: Float
        var yStop: Float

        for (i in 0..11) {
            xStart = x - radiusMain * sin(angle)
            yStart = y + radiusMain * cos(angle)
            xStop = xStart + length * sin(angle)
            yStop = yStart - length * cos(angle)

            canvas.drawLine(
                xStart,
                yStart,
                xStop,
                yStop,
                paint
            )

            angle += 23.7f.toRad
        }
    }

    private fun setSpeedTextNumber(canvas: Canvas) {
        val length = 80
        radiusMain = width / 2f - 140

        val x = width / 2f + 10f
        val y = height / 2f
        var angle = 50f.toRad

        paint.color = Color.BLUE
        paint.strokeWidth = 10f
        paint.style = Paint.Style.FILL
        paint.textSize = 40f

        var xStart: Float
        var yStart: Float
        var xStop: Float
        var yStop: Float

        for (i in 0..11) {
            xStart = x - radiusMain * sin(angle)
            yStart = y + radiusMain * cos(angle)
            xStop = xStart + length * sin(angle) - 15f
            yStop = yStart - length * cos(angle) + 5f
            canvas.drawText("${i * 20}", xStop, yStop, paint)

            angle += 23.7f.toRad
        }
    }

    private fun speedCenter(canvas: Canvas) {
        val x = width / 2f
        val y = height / 2f + 20f
//        paint.strokeWidth = 10f
        paint.textSize = 120f
        paint.textAlign = Paint.Align.CENTER

        canvas.drawText("$currentSpeed", x, y, paint)
    }

    private fun speedOuterLine(canvas: Canvas) {
        radiusMain = width / 2f - 50f
        val x = width / 2f
        val y = height / 2f

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f
        paint.color = Color.BLUE

        canvas.drawArc(
            x - radiusMain,
            y - radiusMain,
            x + radiusMain,
            y + radiusMain,
            140f,
            speedLineValue.toFloat(),
            false,
            paint
        )
    }

    private fun speedIndicator(canvas: Canvas) {
        radiusMain = width / 2f
        val length = 3 * radiusMain / 4

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        paint.color = Color.BLUE

        val xStart = width / 2f
        val yStart = height / 2f
        val xStop = xStart - length * sin(angleIndicator)
        val yStop = yStart + length * cos(angleIndicator)

        canvas.drawLine(
            xStart,
            yStart,
            xStop,
            yStop,
            paint
        )
    }

//    private fun speedLine(canvas: Canvas): Flow<Unit> = flow {
//        radiusMain = width / 2f - 50f
//        val x = width / 2f
//        val y = height / 2f
//
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 50f
//        paint.color = Color.BLUE
//
//        canvas.drawArc(
//            x - radiusMain,
//            y - radiusMain,
//            x + radiusMain,
//            y + radiusMain,
//            140f,
//            speedLineValue.toFloat(),
//            false,
//            paint
//        )
//    }
}