package ru.sibsutis.network.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.interceptor.FakeDataInterceptor
import ru.sibsutis.network.interceptor.provideLoggingInterceptor
import ru.sibsutis.network.okhttp.provideOkHttpClient
import ru.sibsutis.network.retrofit.provideRetrofit

val networkModule = module {
	single { provideLoggingInterceptor() }
	single { FakeDataInterceptor(androidContext()) }
	single(named(MOCK)) {
		provideOkHttpClient(
			interceptors = listOf(
				get<FakeDataInterceptor>()
			)
		)
	}
	single(named(MOCK)) {
		provideRetrofit(
			okHttpClient = get(named(MOCK)),
			url = "https://sibsutis"
		)
	}
}