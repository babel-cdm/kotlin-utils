package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.String.COMMA
import es.babel.cdm.utils.constants.String.DOT

fun Float.fromNumberToNumberWithComma(decimals: String): String =
    decimals.format(this).replace(DOT, COMMA)

fun getMin(floatList: List<Float>): Float {
    var min = floatList.first()
    floatList.forEach { if (it < min) min = it }
    return min
}

fun getMax(floatList: List<Float>): Float {
    var max = floatList.first()
    floatList.forEach { if (it > max) max = it }
    return max
}
