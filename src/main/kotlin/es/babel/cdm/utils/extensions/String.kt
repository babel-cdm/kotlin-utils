package es.babel.cdm.utils.extensions

import android.annotation.SuppressLint
import android.util.Patterns
import es.babel.cdm.utils.constants.Date.TimeZone.UTC
import es.babel.cdm.utils.constants.String.BLANK
import es.babel.cdm.utils.constants.String.COMMA
import es.babel.cdm.utils.constants.String.DIACRITICAL_MARKS
import es.babel.cdm.utils.constants.String.EMPTY
import es.babel.cdm.utils.constants.String.POINT
import es.babel.cdm.utils.constants.String.TWO_DECIMALS
import es.babel.cdm.utils.constants.Validation
import java.text.Normalizer
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

fun String.fromNumberToNumberWithPoint(): Float =
    this.replace(COMMA, POINT).toFloat()

fun String.fromNumberToNumberWithComma(): String {
    TWO_DECIMALS.format(this)
    return this.replace(POINT, COMMA)
}

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = Pattern.compile(Validation.Pattern.PASSWORD)
    .matcher(this).matches()

fun String.isValidPhone() = Pattern.compile(Validation.Pattern.PHONE).matcher(this).matches()

fun String.isValidSpanishPhone() =
    Pattern.compile(Validation.Pattern.SPANISH_PHONE).matcher(this).matches()

fun String.isValidDNI(): Boolean {
    val letters = arrayOf(
        "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X",
        "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"
    )
    if (Pattern.compile(Validation.Pattern.DNI).matcher(this).matches()) {
        val letter = letters[(this.substring(0, 8)).toInt() % 23]
        if (letter == this.last().toUpperCase().toString())
            return true
    }
    return false
}

fun String.isValidNIE(): Boolean {
    val letters = arrayOf(
        "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X",
        "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"
    )

    if (Pattern.compile(Validation.Pattern.NIE).matcher(this).matches()) {
        var nie = ""
        when (this.first().toUpperCase().toString()) {
            "X" -> nie = this.replace(this.first().toString(), "0")
            "Y" -> nie = this.replace(this.first().toString(), "1")
            "Z" -> nie = this.replace(this.first().toString(), "2")
        }
        val letter = letters[(nie.substring(0, 8)).toInt() % 23]
        if (letter == nie.last().toUpperCase().toString())
            return true
    }
    return false
}

@SuppressLint("DefaultLocale")
fun String.capitalizeWords() =
    this.toLowerCase(Locale.getDefault()).split(BLANK)
        .joinToString(separator = BLANK) { it.capitalize() }

fun String.toPriceString(): String {
    return String.format(es.babel.cdm.utils.constants.String.Format.PRICE, this)
}

fun String.normalizeText() = Normalizer.normalize(
    this,
    Normalizer.Form.NFD
).replace(DIACRITICAL_MARKS.toRegex(), EMPTY)
