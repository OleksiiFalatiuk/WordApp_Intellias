package com.example.wordapp.provider

import com.example.wordapp.data.retrofit.WordApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

class NetworkModule{
    private val baseUrl = "https://api.dictionaryapi.dev/"
    private val version = "2/"
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .addInterceptor(ApiInterceptor())
        .build()


    @ExperimentalSerializationApi
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseUrl + version)
        .client(httpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    @ExperimentalSerializationApi
    val api: WordApiService = retrofitBuilder.create()
}

class ApiInterceptor: Interceptor {

    companion object{
        private const val API = "api"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val urlBuilder = origin.url.newBuilder()
        val url = urlBuilder
            .addQueryParameter("api_key", API)
            .build()

        val requestBuilder = origin.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}