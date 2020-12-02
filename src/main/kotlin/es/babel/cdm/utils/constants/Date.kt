package es.babel.cdm.utils.constants

object Date {
    const val MONTHS_IN_A_YEAR = 12
    const val FIRST_DAY_OF_THE_MONTH = 1

    object Pattern {
        const val YEAR = "YYYY"
        const val MONTH = "MM"
        const val MONTH_OF_THE_YEAR = "MMMM"
        const val DAY = "dd"
        const val DAY_OF_THE_WEEK = "EEEE"
        const val HOUR = "HH"
        const val MINUTE = "mm"
        const val SECOND = "ss"
        const val FULL_DATE_AND_HOUR = "dd/MM/yyyy HH:mm:ss"
        const val FULL_DATE = "dd/MM/yyyy"
        const val MONTH_AND_DAY = "dd/MM"
        const val FULL_HOUR = "HH:mm:ss"
        const val HOUR_AND_MINUTE = "HH:mm"
        const val MONTH_AND_YEAR = "MMMM YYYY"
    }

    object TimeZone {
        const val UTC = "UTC"
    }
}
