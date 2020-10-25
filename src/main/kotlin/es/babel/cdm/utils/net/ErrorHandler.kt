package es.babel.cdm.utils.net

import okhttp3.Response

interface ErrorHandler {
    fun handleError(response: Response): Throwable
}
