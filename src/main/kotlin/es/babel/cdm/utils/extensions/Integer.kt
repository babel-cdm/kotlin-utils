package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Integer.ONE
import es.babel.cdm.utils.constants.Integer.TWO
import es.babel.cdm.utils.constants.Integer.ZERO

fun Int.inHalf() = this / TWO

fun Int.toBoolean() = this == ONE

fun Int?.checkNull(defaultValue: Int = ZERO): Int {
    return this ?: defaultValue
}
