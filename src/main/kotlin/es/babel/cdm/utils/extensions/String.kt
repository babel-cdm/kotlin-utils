package es.babel.cdm.utils.extensions

import android.util.Patterns
import es.babel.cdm.utils.constants.TimeZone.UTC
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.TimeZone.getTimeZone

fun String.toDate(inputFormat: String, inputTimeZone: String? = null): Date? =
    kotlin.runCatching {
        SimpleDateFormat(inputFormat, Locale.getDefault()).also { simpleDateFormat ->
            simpleDateFormat.timeZone =
                inputTimeZone?.let { getTimeZone(inputTimeZone) } ?: TimeZone.getDefault()
        }.parse(this)
    }.onFailure { error -> Timber.e(error) }.getOrNull()

fun String.localeStringDateToUtcStringDate(inputFormat: String, outputFormat: String): String? =
    this.toDate(inputFormat)?.toString(outputFormat, UTC)

fun String.utcStringDateToLocaleStringDate(inputFormat: String, outputFormat: String): String? =
    this.toDate(inputFormat, UTC)?.toString(outputFormat)

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = this.isNotEmpty()