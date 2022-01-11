package ru.sibsutis.network.okhttp

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 60

fun provideOkHttpClient(interceptors: List<Interceptor>): OkHttpClient =
	OkHttpClient().newBuilder().apply {
		connectTimeout(TIMEOUT, TimeUnit.SECONDS)
		writeTimeout(TIMEOUT, TimeUnit.SECONDS)
		readTimeout(TIMEOUT, TimeUnit.SECONDS)

		interceptors.map { addInterceptor(it) }
	}.build()