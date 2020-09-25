package es.babel.cdm.utils.extensions

import okhttp3.internal.UTC
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.toDate(inputFormat: String, inputTimeZone: TimeZone = TimeZone.getDefault()): Date? =
    kotlin.runCatching {
        SimpleDateFormat(inputFormat, Locale.getDefault()).also { simpleDateFormat ->
            simpleDateFormat.timeZone = inputTimeZone
        }.parse(this)
    }.onFailure { error -> Timber.e(error) }.getOrNull()

fun String.localeStringDateToUtcStringDate(inputFormat: String, outputFormat: String): String? =
    this.toDate(inputFormat)?.toString(outputFormat, UTC)

fun String.utcStringDateToLocaleStringDate(inputFormat: String, outputFormat: String): String? =
    this.toDate(inputFormat, UTC)?.toString(outputFormat)
