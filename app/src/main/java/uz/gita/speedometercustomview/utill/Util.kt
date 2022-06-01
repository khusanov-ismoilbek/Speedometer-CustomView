package uz.gita.speedometercustomview.utill

import kotlin.math.PI

val Float.toRad: Float get() = (this * PI / 180).toFloat()