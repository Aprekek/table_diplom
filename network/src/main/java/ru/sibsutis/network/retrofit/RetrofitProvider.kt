package ru.sibsutis.network.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideRetrofit(
	okHttpClient: OkHttpClient,
	url: String
): Retrofit = Retrofit.Builder()
	.addConverterFactory(MoshiConverterFactory.create(moshi))
	.client(okHttpClient)
	.baseUrl(url)
	.build()

private val moshi: Moshi = Moshi.Builder()
	.add(KotlinJsonAdapterFactory())
	.build()