package ru.sibsutis.network.okhttp

import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient

private const val TIMEOUT: Long = 60

fun provideOkHttpClient(interceptors: List<Interceptor>): OkHttpClient =
	OkHttpClient().newBuilder().apply {
		connectTimeout(TIMEOUT, TimeUnit.SECONDS)
		writeTimeout(TIMEOUT, TimeUnit.SECONDS)
		readTimeout(TIMEOUT, TimeUnit.SECONDS)

		interceptors.map { addInterceptor(it) }
	}.build()