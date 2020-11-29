package es.babel.cdm.utils.constants

object Validation {
    object Pattern {
        const val PASSWORD =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\$€\\[\\]<>@#&*¿?¡!|\\/\\\\\\-_]{8,16}\$"
        const val PHONE = "^[0-9]{4,14}\$"
        const val SPANISH_PHONE = "^[0-9]{9}\$"
    }
}
