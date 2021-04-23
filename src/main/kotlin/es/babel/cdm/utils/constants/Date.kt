package es.babel.cdm.utils.constants

object Date {
    const val MONTHS_IN_A_YEAR = 12
    const val FIRST_DAY_OF_THE_MONTH = 1
    const val MILLISECONDS_IN_A_MINUTE = 60000L
    val MINUTES_RANGE_IN_A_DAY = Pair(0, 1439)

    object Pattern {
        const val YEAR = "yyyy"
        const val SHORT_YEAR = "yy"
        const val MONTH = "MM"
        const val MONTH_OF_THE_YEAR = "MMMM"
        const val SHORT_MONTH_OF_THE_YEAR = "MMM"
        const val DAY = "dd"
        const val DAY_OF_THE_WEEK = "EEEE"
        const val HOUR = "HH"
        const val MINUTE = "mm"
        const val SECOND = "ss"
        const val FULL_DATE = "dd/MM/yyyy"
        const val FULL_DATE_AND_HOUR = "dd/MM/yyyy HH:mm:ss"
        const val FULL_DATE_HOUR_AND_MINUTE = "dd/MM/yyyy HH:mm"
        const val FULL_DATE_DASH = "dd-MM-yyyy"
        const val FULL_DATE_DASH_REVERSED = "yyyy-MM-dd"
        const val FULL_DATE_DASH_HOUR_AND_MINUTE = "dd-MM-yyyy HH:mm"
        const val MONTH_AND_DAY = "dd/MM"
        const val FULL_HOUR = "HH:mm:ss"
        const val HOUR_AND_MINUTE = "HH:mm"
        const val MONTH_AND_YEAR = "MMMM yyyy"
        const val DAY_AND_MONTH = "EEEE dd MMMM"
        const val DAY_OF_WEEK_COMMA_DAY_OF_MONTH = "EEE, d 'de' MMM"
    }

    object TimeZone {
        const val UTC = "UTC"
    }
}
