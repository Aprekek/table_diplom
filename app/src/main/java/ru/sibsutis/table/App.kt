package ru.sibsutis.table

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.ERROR
import ru.sibsutis.network.di.networkModule
import ru.sibsutis.table.database.di.databaseModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(ERROR)
			androidContext(this@App)

			modules(
				networkModule,
				databaseModule,
			)
		}
	}
}