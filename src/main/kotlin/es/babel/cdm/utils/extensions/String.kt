package es.babel.cdm.utils.extensions

import android.util.Patterns
import es.babel.cdm.utils.constants.Date.TimeZone.UTC
import es.babel.cdm.utils.constants.Validation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.regex.Pattern
import timber.log.Timber

fun String.toDate(inputFormat: String, inputTimeZone: String? = null): Date? =
    kotlin.runCatching {
        SimpleDateFormat(inputFormat, Locale.getDefault()).also { simpleDateFormat ->
            simpleDateFormat.timeZone =
                inputTimeZone?.let { TimeZone.getTimeZone(inputTimeZone) } ?: TimeZone.getDefault()
        }.parse(this)
    }.onFailure { error -> Timber.e(error) }.getOrNull()

fun String.localeStringDateToUtcStringDate(inputFormat: String, outputFormat: String): String? =
    this.toDate(inputFormat)?.toString(outputFormat, UTC)

fun String.utcStringDateToLocaleStringDate(inputFormat: String, outputFormat: String): String? =
    this.toDate(inputFormat, UTC)?.toString(outputFormat)

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = Pattern.compile(Validation.Pattern.PASSWORD)
    .matcher(this).matches()
