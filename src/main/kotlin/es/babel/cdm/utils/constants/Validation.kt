package es.babel.cdm.utils.constants

object Validation {
    object Pattern {
        const val PASSWORD =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\$€\\[\\]<>@#&*¿?¡!|\\/\\\\\\-_]{8,16}\$"
    }
}
