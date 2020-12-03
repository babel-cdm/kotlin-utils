package es.babel.cdm.utils.extensions


fun Float.fromNumberToNumberWithComma(): String =
    "%.2f".format(this).replace(POINT, COMMA)
