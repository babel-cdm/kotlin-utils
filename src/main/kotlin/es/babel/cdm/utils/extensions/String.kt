package es.babel.cdm.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import androidx.core.text.HtmlCompat
import es.babel.cdm.utils.constants.Date.TimeZone.UTC
import es.babel.cdm.utils.constants.String.BAR
import es.babel.cdm.utils.constants.String.BLANK
import es.babel.cdm.utils.constants.String.COLON
import es.babel.cdm.utils.constants.String.COMMA
import es.babel.cdm.utils.constants.String.DASH
import es.babel.cdm.utils.constants.String.DEFAULT_DOUBLE
import es.babel.cdm.utils.constants.String.DIACRITICAL_MARKS
import es.babel.cdm.utils.constants.String.DOT
import es.babel.cdm.utils.constants.String.EMPTY
import es.babel.cdm.utils.constants.String.FALSE
import es.babel.cdm.utils.constants.String.Format.POINTS
import es.babel.cdm.utils.constants.String.Format.PRICE
import es.babel.cdm.utils.constants.String.Format.WHOLE_NUMBER_FORMAT
import es.babel.cdm.utils.constants.String.HEXADECIMAL_CHARS
import es.babel.cdm.utils.constants.String.TRUE
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
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_CHARACTER_ONE
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_CHARACTER_TWO
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_CHARACTER_ZERO
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_DEFAULT_SUM
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_MODULE_DEFAULT_RESULT
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_MODULE_OF_TEN
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_MODULE_OF_TWO
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_MULTIPLY_BY_TWO
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_NEXT_CHARACTER
import es.babel.cdm.utils.constants.Validation.LuhnValidation.LUHN_VALUE_LIMIT
import java.text.DecimalFormat
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
    if (!isNullOrEmpty()) replace(COMMA, DOT).toFloat() else DEFAULT_DOUBLE.toFloat()

fun String.fromNumberToNumberWithComma(): String =
    TWO_DECIMALS.format(this).replace(DOT, COMMA)

fun String.isValidEmail() = Pattern.compile(Validation.Pattern.EMAIL)
    .matcher(this).matches()

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
    ] == last().uppercaseChar().toString()
}

fun String.isDni(): Boolean = matches(Validation.Pattern.DNI.toRegex())

fun String.isNie(): Boolean = matches(Validation.Pattern.NIE.toRegex())

fun String.isValidNIE(): Boolean {
    return if (Pattern.compile(Validation.Pattern.NIE).matcher(this).matches()) {
        val nie = when (first().uppercaseChar().toString()) {
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
                .uppercaseChar()
                .toString()
            )
    } else false
}

fun String.capitalizeWords() = lowercase(Locale.getDefault()).split(BLANK)
    .joinToString(separator = BLANK) { it.capitalize() }.split(BAR)
    .joinToString(separator = BAR) { it.capitalize() }.split(DASH)
    .joinToString(separator = DASH) { it.capitalize() }

fun String.toPriceString() = String.format(PRICE, this)

fun String.toPointsString() = String.format(POINTS, this)

fun String.toNumberWithDots(): String {
    val doubleValue = runCatching {
        fromNumberToNumberWithPoint().toDouble()
    }.getOrDefault(DEFAULT_DOUBLE)

    return DecimalFormat(WHOLE_NUMBER_FORMAT).format(doubleValue).replace(COMMA, DOT)
}

fun String.replaceDotsByColon() = replace(DOT, COLON)

fun String.normalizeText() = Normalizer.normalize(
    this,
    Normalizer.Form.NFD
).replace(DIACRITICAL_MARKS.toRegex(), EMPTY)

fun String.hexStringToByteArray(): ByteArray {
    val len = this.length
    val result = ByteArray(len / 2)
    (0 until len step 2).forEach { i ->
        result[i.shr(1)] =
            HEXADECIMAL_CHARS.indexOf(this[i])
                .shl(4)
                .or(HEXADECIMAL_CHARS.indexOf(this[i + 1]))
                .toByte()
    }
    return result
}

fun String?.checkNull(defaultValue: String = EMPTY) = this ?: defaultValue

fun String.validateFormulaLuhn(): Boolean {
    var sum = LUHN_DEFAULT_SUM
    indices.forEach {
        var value = substring(it, it + LUHN_NEXT_CHARACTER).toInt()
        if (it % LUHN_MODULE_OF_TWO == LUHN_MODULE_DEFAULT_RESULT) {
            value *= LUHN_MULTIPLY_BY_TWO
            if (value > LUHN_VALUE_LIMIT) {
                val valueS = value.toString()
                value = valueS.substring(
                    LUHN_CHARACTER_ZERO,
                    LUHN_CHARACTER_ONE
                )
                    .toInt() + valueS.substring(
                    LUHN_CHARACTER_ONE,
                    LUHN_CHARACTER_TWO
                ).toInt()
            }
        }
        sum += value
    }
    return sum % LUHN_MODULE_OF_TEN == LUHN_MODULE_DEFAULT_RESULT
}

fun String.toBoolean() =
    when (lowercase(Locale.getDefault())) {
        TRUE -> true
        FALSE -> false
        else -> false
    }

fun String.capitalize() = replaceFirstChar(Char::titlecase)

@SuppressLint("DefaultLocale")
fun String.capitalizeDate(): String =
    capitalize().replace(DOT, EMPTY)

fun String.convertFromHtml(): Spanned =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

/**
 * Default string style must be set in the xml layout.
 * Returns a spannable text with custom font from a string.
 *
 * @param spannableStringList the list of strings to set the different style font
 * @param typeface TypefaceStyle to set to custom string
 */
fun String.spannableStringCustomTypeface(
    spannableStringList: List<String>,
    typeface: Typeface
): SpannableString {
    val spannable = SpannableString(this)
    spannableStringList.forEach {
        spannable.setSpan(
            Spannable(typeface), indexOf(it),
            indexOf(it) + it.length,
            android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannable
}

/**
 * Default string style must be set in the xml layout.
 * Returns a spannable text with custom style from a string.
 *
 * @param spannableStringList the list of strings to set the different style font
 * @param style Style to set to custom string
 */
fun String.spannableStringCustomStyle(
    context: Context,
    spannableStringList: List<String>,
    style: Int
): SpannableString {
    val spannable = SpannableString(this)
    spannableStringList.forEach {
        spannable.setSpan(
            TextAppearanceSpan(context, style), indexOf(it),
            indexOf(it) + it.length,
            android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannable
}

fun String.addDotAndBlank(): String = this + DOT + BLANK

fun String.deleteSpaces(): String = replace(BLANK, EMPTY)

fun String.joinStringWithSpace(second: String): String = this + BLANK + second

fun String.getNumbers(): String = filter { it.isDigit() }

fun String.getLetters(): String = filter { it.isLetter() }

fun String.containsAny(keywords: List<String>): Boolean = keywords.any { it in this }

fun String.removeCharacter(character: String): String = replace(character, EMPTY)

fun String.removeLeadingZeros() : String = if (this.isNotEmpty()) Integer.valueOf(this).toString() else this