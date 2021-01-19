package es.babel.cdm.utils.constants

object String {
    const val BLANK = " "
    const val DASH = "-"
    const val COMMA = ","
    const val EMPTY = ""
    const val DIACRITICAL_MARKS = "[\\p{InCombiningDiacriticalMarks}]+"
    const val DOT = "."
    const val COLON = ":"
    const val TWO_DECIMALS = "%.2f"
    const val BAR = "/"
    const val HEXADECIMAL_CHARS = "0123456789ABCDEF"

    object Format {
        const val PRICE = "%s €"
        const val POINTS = "%s pts"
    }
}
