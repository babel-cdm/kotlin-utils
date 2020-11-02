package es.babel.cdm.utils.extensions

import es.babel.cdm.utils.constants.Array.Index

fun Int.previousIndex(): Int = this - Index.PREVIOUS

fun Int.nextIndex(): Int = this + Index.NEXT
