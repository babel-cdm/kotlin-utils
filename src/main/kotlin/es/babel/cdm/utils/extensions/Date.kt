package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Date.FIRST_DAY_OF_THE_MONTH
import es.babel.cdm.utils.constants.Date.Pattern.DAY
import es.babel.cdm.utils.constants.Date.Pattern.DAY_OF_THE_WEEK
import es.babel.cdm.utils.constants.Date.Pattern.HOUR
import es.babel.cdm.utils.constants.Date.Pattern.MINUTE
import es.babel.cdm.utils.constants.Date.Pattern.MONTH
import es.babel.cdm.utils.constants.Date.Pattern.MONTH_OF_THE_YEAR
import es.babel.cdm.utils.constants.Date.Pattern.SECOND
import es.babel.cdm.utils.constants.Date.Pattern.SHORT_MONTH_OF_THE_YEAR
import es.babel.cdm.utils.constants.Date.Pattern.YEAR
import java.text.SimpleDateFormat
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone
import java.util.TimeZone.getTimeZone
import timber.log.Timber

fun Date.toString(outputFormat: String, outputTimeZone: String? = null): String? =
    kotlin.runCatching {
        SimpleDateFormat(outputFormat, Locale.getDefault()).also { simpleDateFormat ->
            simpleDateFormat.timeZone =
                outputTimeZone?.let { getTimeZone(outputTimeZone) } ?: TimeZone.getDefault()
        }.format(this)
    }.onFailure { error -> Timber.e(error) }.getOrNull()

fun Date.year(): String? = this.toString(YEAR)

fun Date.month(): String? = this.toString(MONTH)

fun Date.monthOfTheYear(): String? = this.toString(MONTH_OF_THE_YEAR)

fun Date.shortMonthOfTheYear(): String? = this.toString(SHORT_MONTH_OF_THE_YEAR)

fun Date.day(): String? = this.toString(DAY)

fun Date.dayOfTheWeek(): String? = this.toString(DAY_OF_THE_WEEK)

fun Date.hour(): String? = this.toString(HOUR)

fun Date.minute(): String? = this.toString(MINUTE)

fun Date.second(): String? = this.toString(SECOND)

fun monthAndYearToDate(month: Int, year: Int): Date =
    GregorianCalendar(year, month, FIRST_DAY_OF_THE_MONTH).time
