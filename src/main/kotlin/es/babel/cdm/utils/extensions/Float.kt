package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.String.COMMA
import es.babel.cdm.utils.constants.String.POINT

fun Float.fromNumberToNumberWithComma(decimals: String): String =
    decimals.format(this).replace(POINT, COMMA)
