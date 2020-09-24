package es.babel.cdm.utils.net

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
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

        fun build(): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setPrettyPrinting()
                        .disableHtmlEscaping()
                        .serializeNulls()
                        .create()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .client(
                HttpClient.Builder()
                    .ignoreSSL(ignoreSSL)
                    .also { builder ->
                        interceptors.forEach { interceptor ->
                            builder.interceptor(interceptor)
                        }
                    }
                    .interceptor(httpLoggingInterceptor)
                    .build()
            )
            .build()
    }

    companion object {
        const val DEFAULT_BASE_URL = ""
    }
}
