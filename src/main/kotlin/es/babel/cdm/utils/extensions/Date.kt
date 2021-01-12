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
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.TimeZone.getTimeZone

fun Date.toString(outputFormat: String, outputTimeZone: String? = null): String? =
    kotlin.runCatching {
        SimpleDateFormat(outputFormat, Locale.getDefault()).also { simpleDateFormat ->
            simpleDateFormat.timeZone =
                outputTimeZone?.let { getTimeZone(outputTimeZone) } ?: TimeZone.getDefault()
        }.format(this)
    }.onFailure { error -> Timber.e(error) }.getOrNull()

fun Date.year(outputTimeZone: String? = null): String? = this.toString(YEAR, outputTimeZone)

fun Date.month(outputTimeZone: String? = null): String? = this.toString(MONTH, outputTimeZone)

fun Date.monthOfTheYear(outputTimeZone: String? = null): String? = this.toString(MONTH_OF_THE_YEAR, outputTimeZone)

fun Date.day(outputTimeZone: String? = null): String? = this.toString(DAY, outputTimeZone)

fun Date.shortMonthOfTheYear(): String? = this.toString(SHORT_MONTH_OF_THE_YEAR)

fun Date.dayOfTheWeek(outputTimeZone: String? = null): String? = this.toString(DAY_OF_THE_WEEK, outputTimeZone)

fun Date.hour(outputTimeZone: String? = null): String? = this.toString(HOUR, outputTimeZone)

fun Date.minute(outputTimeZone: String? = null): String? = this.toString(MINUTE, outputTimeZone)

fun Date.second(outputTimeZone: String? = null): String? = this.toString(SECOND, outputTimeZone)

fun monthAndYearToDate(month: Int, year: Int): Date =
    GregorianCalendar(year, month, FIRST_DAY_OF_THE_MONTH).time
