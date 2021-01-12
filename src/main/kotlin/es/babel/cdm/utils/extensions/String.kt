package es.babel.cdm.utils.extensions

import android.annotation.SuppressLint
import android.util.Patterns
import es.babel.cdm.utils.constants.Date.TimeZone.UTC
import es.babel.cdm.utils.constants.String.BLANK
import es.babel.cdm.utils.constants.String.COLON
import es.babel.cdm.utils.constants.String.COMMA
import es.babel.cdm.utils.constants.String.DASH
import es.babel.cdm.utils.constants.String.DIACRITICAL_MARKS
import es.babel.cdm.utils.constants.String.DOT
import es.babel.cdm.utils.constants.String.EMPTY
import es.babel.cdm.utils.constants.String.Format.PRICE
import es.babel.cdm.utils.constants.String.TWO_DECIMALS
import es.babel.cdm.utils.constants.Validation
import es.babel.cdm.utils.constants.Validation.Document.CALCULATE_LETTER
import es.babel.cdm.utils.constants.Validation.Document.DOCUMENT_LETTERS
import es.babel.cdm.utils.constants.Validation.Document.EMPTY_DOCUMENT
import es.babel.cdm.utils.constants.Validation.Document.END_DOCUMENT
import es.babel.cdm.utils.constants.Validation.Document.START_DOCUMENT
import es.babel.cdm.utils.constants.Validation.Document.X_LETTER
import es.babel.cdm.utils.constants.Validation.Document.X_LETTER_TO_NUMBER_STRING
import es.babel.cdm.utils.constants.Validation.Document.Y_LETTER
import es.babel.cdm.utils.constants.Validation.Document.Y_LETTER_TO_NUMBER_STRING
import es.babel.cdm.utils.constants.Validation.Document.Z_LETTER
import es.babel.cdm.utils.constants.Validation.Document.Z_LETTER_TO_NUMBER_STRING
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

fun String.fromNumberToNumberWithPoint(): Float = replace(COMMA, DOT).toFloat()

fun String.fromNumberToNumberWithComma(): String =
    TWO_DECIMALS.format(this).replace(DOT, COMMA)

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = Pattern.compile(Validation.Pattern.PASSWORD)
    .matcher(this).matches()

fun String.isValidPhone() = Pattern.compile(Validation.Pattern.PHONE).matcher(this).matches()

fun String.isValidSpanishPhone() =
    Pattern.compile(Validation.Pattern.SPANISH_PHONE).matcher(this).matches()

fun String.isValidDNI(): Boolean {
    return (Pattern.compile(Validation.Pattern.DNI).matcher(this).matches()) && DOCUMENT_LETTERS[
        (
            substring(
                START_DOCUMENT, END_DOCUMENT
            )
            ).toInt() % CALCULATE_LETTER
    ] == last().toUpperCase().toString()
}

fun String.isValidNIE(): Boolean {
    return if (Pattern.compile(Validation.Pattern.NIE).matcher(this).matches()) {
        val nie = when (first().toUpperCase().toString()) {
            X_LETTER -> replace(first().toString(), X_LETTER_TO_NUMBER_STRING)
            Y_LETTER -> replace(first().toString(), Y_LETTER_TO_NUMBER_STRING)
            Z_LETTER -> replace(first().toString(), Z_LETTER_TO_NUMBER_STRING)
            else -> EMPTY_DOCUMENT
        }
        (
            DOCUMENT_LETTERS[
                (
                    nie.substring(
                        START_DOCUMENT, END_DOCUMENT
                    )
                    ).toInt() % CALCULATE_LETTER
            ] == nie.last()
                .toUpperCase()
                .toString()
            )
    } else false
}

@SuppressLint("DefaultLocale")
fun String.capitalizeWords() = toLowerCase(Locale.getDefault()).split(BLANK, DASH)
    .joinToString(separator = BLANK) { it.capitalize() }

fun String.toPriceString() = String.format(PRICE, this)

fun String.toPointsString(): String {
    return String.format(es.babel.cdm.utils.constants.String.Format.POINTS, this)
}

fun String.replaceDotsByColon() = replace(DOT, COLON)

fun String.normalizeText() = Normalizer.normalize(
    this,
    Normalizer.Form.NFD
).replace(DIACRITICAL_MARKS.toRegex(), EMPTY)
