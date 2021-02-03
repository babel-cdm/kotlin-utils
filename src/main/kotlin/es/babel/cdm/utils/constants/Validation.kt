package es.babel.cdm.utils.constants

object Validation {
    object Pattern {
        const val PASSWORD =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\$€\\[\\]<>@#&*¿?¡!|\\/\\\\\\-_]{8,16}\$"
        const val PHONE = "^[0-9]{4,15}\$"
        const val SPANISH_PHONE = "^[6,7]{1}[0-9]{8}\$"
        const val NIE = "^[XxYyZz]{1}[0-9]{7}[a-zA-Z]{1}\$"
        const val DNI = "^\\d{8}[a-zA-Z]{1}\$"
        const val EMAIL = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"
        const val DEFAULT_TEXT_DATA_ALLOWED_CHARACTERS =
            "aáàâäbcçdeéèêëfghiíìîïjklmnñoóòöôpqrstuúùûüvwxyzßAÁÀÄÂBCÇDEÉÈÊËFGHIÍÌÎÏJ" +
                "KLMNÑOÓÒÖÔPQRSTUÚÙÜÛVWXYZẞ\' "
        const val DEFAULT_PHONE_DATA_ALLOWED_CHARACTERS = "0123456789"
        const val DEFAULT_TEXT_ALPHANUMERIC_DOCUMENT_ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOP" +
            "QRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    }

    object Document {
        const val START_DOCUMENT = 0
        const val END_DOCUMENT = 8
        const val EMPTY_DOCUMENT = ""
        const val X_LETTER = "X"
        const val X_LETTER_TO_NUMBER_STRING = "0"
        const val Y_LETTER = "Y"
        const val Y_LETTER_TO_NUMBER_STRING = "1"
        const val Z_LETTER = "Z"
        const val Z_LETTER_TO_NUMBER_STRING = "2"
        const val CALCULATE_LETTER = 23
        val DOCUMENT_LETTERS = arrayOf(
            "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X",
            "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"
        )
    }

    object LuhnValidation {
        const val LUHN_DEFAULT_SUM = 0
        const val LUHN_NEXT_CHARACTER = 1
        const val LUHN_MODULE_OF_TWO = 2
        const val LUHN_MODULE_OF_TEN = 10
        const val LUHN_MODULE_DEFAULT_RESULT = 0
        const val LUHN_MULTIPLY_BY_TWO = 2
        const val LUHN_CHARACTER_ZERO = 0
        const val LUHN_CHARACTER_ONE = 1
        const val LUHN_CHARACTER_TWO = 2
        const val LUHN_VALUE_LIMIT = 9
    }
}
