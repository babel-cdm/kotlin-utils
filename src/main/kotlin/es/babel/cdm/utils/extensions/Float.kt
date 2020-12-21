package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Float.Format.TWO_DECIMALS
import es.babel.cdm.utils.constants.String.COMMA
import es.babel.cdm.utils.constants.String.POINT

fun Float.fromNumberToNumberWithComma(): String =
    TWO_DECIMALS.format(this).replace(POINT, COMMA)
