package ru.sibsutis.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor

fun provideLoggingInterceptor(): HttpLoggingInterceptor =
	HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)