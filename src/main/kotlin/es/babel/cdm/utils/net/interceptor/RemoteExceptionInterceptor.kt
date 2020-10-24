package es.babel.cdm.utils.net.interceptor

import es.babel.cdm.utils.net.ErrorHandler
import okhttp3.Interceptor

class RemoteExceptionInterceptor(private val errorHandler: ErrorHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        kotlin.runCatching {
            chain.proceed(chain.request()).also { response ->
                if (response.code >= MIN_HTTP_ERROR_CODE)
                    throw errorHandler.handleError(response)
            }
        }.getOrThrow()

    companion object {
        const val MIN_HTTP_ERROR_CODE = 300
    }
}