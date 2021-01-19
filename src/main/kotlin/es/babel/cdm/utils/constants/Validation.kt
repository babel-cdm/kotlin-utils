package es.babel.cdm.utils.constants

object Validation {
    object Pattern {
        const val PASSWORD =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\$€\\[\\]<>@#&*¿?¡!|\\/\\\\\\-_]{8,16}\$"
        const val PHONE = "^[0-9]{4,14}\$"
        const val SPANISH_PHONE = "^[0-9]{9}\$"
        const val NIE = "^[XxYyZz]{1}[0-9]{7}[a-zA-Z]{1}\$"
        const val DNI = "^\\d{8}[a-zA-Z]{1}\$"
        const val DEFAULT_TEXT_DATA_ALLOWED_CHARACTERS =
            "aáàâäbcçdeéèêëfghiíìîïjklmnñoóòöôpqrstuúùûüvwxyzßAÁÀÄÂBCÇDEÉÈÊËFGHIÍÌÎÏJ" +
                "KLMNÑOÓÒÖÔPQRSTUÚÙÜÛVWXYZẞ\' "
        const val DEFAULT_PHONE_DATA_ALLOWED_CHARACTERS = "0123456789"
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
}
