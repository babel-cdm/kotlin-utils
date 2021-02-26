package es.babel.cdm.utils.extensions

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class Spannable(private val typeface: Typeface?) : MetricAffectingSpan() {
    override fun updateDrawState(paint: TextPaint) {
        paint.typeface = typeface
    }

    override fun updateMeasureState(paint: TextPaint) {
        paint.typeface = typeface
    }
}