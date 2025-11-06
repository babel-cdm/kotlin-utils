package es.babel.cdm.utils.net

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import javax.net.ssl.TrustManager
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Retrofit {

    class Builder() {

        private val httpLoggingInterceptor = HttpLoggingInterceptor()
        private var baseUrl: String = DEFAULT_BASE_URL
        private var ignoreSSL: Boolean = true
        private var interceptors: List<Interceptor> = listOf()
        private var trustManagers: List<TrustManager> = emptyList()
        private val certificatePinners = mutableListOf<CertificatePinner>()

        fun httpLoggingInterceptorLevel(level: HttpLoggingInterceptor.Level) =
            this.also { builder ->
                builder.httpLoggingInterceptor.level = level
            }

        fun baseUrl(baseUrl: String) = this.also { builder ->
            builder.baseUrl = baseUrl
        }

        fun ignoreSSL(ignoreSSL: Boolean) = this.also { builder ->
            builder.ignoreSSL = ignoreSSL
        }

        fun interceptors(interceptors: List<Interceptor>) = this.also { builder ->
            builder.interceptors = interceptors
        }

        fun certificatePinner(host: String, vararg pins: String) = apply {
            certificatePinners.add(CertificatePinner.Builder().add(host, *pins).build())
        }

        fun trustManagers(trustManagers: List<TrustManager>) = apply {
            this.trustManagers = trustManagers
        }

        fun build(shortTimeout: Boolean? = false, serializeNulls: Boolean = true): Retrofit =
            build(getOkHttpClient(shortTimeout = shortTimeout), serializeNulls = serializeNulls)

        fun build(timeoutConfig: TimeoutConfig, serializeNulls: Boolean = true): Retrofit =
            build(getOkHttpClient(timeoutConfig = timeoutConfig), serializeNulls = serializeNulls)

        private fun build(okHttpClient: OkHttpClient, serializeNulls: Boolean = true): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setPrettyPrinting()
                        .disableHtmlEscaping()
                        .apply {
                            if (serializeNulls) {
                                serializeNulls()
                            }
                        }
                        .create()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .client(okHttpClient)
            .build()

        private fun getOkHttpClient(shortTimeout: Boolean?): OkHttpClient =
            getOkHttpClientBuilder().build(shortTimeout = shortTimeout ?: false)

        private fun getOkHttpClient(timeoutConfig: TimeoutConfig): OkHttpClient =
            getOkHttpClientBuilder().build(timeoutConfig)

        private fun getOkHttpClientBuilder() = HttpClient.Builder()
            .ignoreSSL(ignoreSSL)
            .certificatePinners(certificatePinners)
            .trustManagers(trustManagers)
            .interceptors(interceptors + httpLoggingInterceptor)
    }

    companion object {
        const val DEFAULT_BASE_URL = ""
    }
}
