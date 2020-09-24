package es.babel.cdm.utils.net

import android.annotation.SuppressLint
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class HttpClient {

    class Builder(
        private var ignoreSSL: Boolean = true,
        private var interceptors: MutableList<Interceptor> = mutableListOf(),
        private var trustManagers: Array<TrustManager> = arrayOf(object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                // Nothing to do
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                // Nothing to do
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })
    ) {
        fun ignoreSSL(ignoreSSL: Boolean) = apply { this.ignoreSSL = ignoreSSL }
        fun interceptor(interceptor: Interceptor) =
            apply { interceptors.add(interceptor) }

        fun build(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)

            interceptors.forEach { interceptor ->
                builder.addInterceptor(interceptor)
            }

            if (ignoreSSL) {
                val sslContext = SSLContext.getInstance(SSL_PROTOCOL)
                sslContext.init(null, trustManagers, java.security.SecureRandom())

                builder
                    .sslSocketFactory(
                        sslContext.socketFactory,
                        trustManagers.first() as X509TrustManager
                    )
                    .hostnameVerifier(HostnameVerifier { _, _ -> true })
            }

            return builder.build()
        }
    }

    companion object {
        const val CONNECT_TIMEOUT_SECONDS = 90L
        const val READ_TIMEOUT_SECONDS = 90L
        const val WRITE_TIMEOUT_SECONDS = 90L
        const val SSL_PROTOCOL = "SSL"
    }
}
