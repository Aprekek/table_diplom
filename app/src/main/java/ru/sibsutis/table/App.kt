package ru.sibsutis.table

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.ERROR
import ru.sibsutis.network.di.networkModule
import ru.sibsutis.table.database.di.databaseModule
import ru.sibsutis.table.di.appModule
import ru.sibsutis.table.feature.groups.changegroup.di.changeGroupModule
import ru.sibsutis.table.feature.groups.startingscreen.di.startingScreenModule
import ru.sibsutis.table.feature.timetable.di.timetableModule
import ru.sibsutis.table.features.teachers.listscreen.di.teachersListScreenModule
import ru.sibsutis.table.navigation.di.navigationModule
import ru.sibsutis.table.preferences.di.preferencesModule
import ru.sibsutis.table.shared.group.data.data.di.groupDataModule
import ru.sibsutis.table.shared.group.domain.di.groupDomainModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(ERROR)
			androidContext(this@App)

			modules(
				appModule,
				networkModule,
				databaseModule,
				preferencesModule,
				startingScreenModule,
				navigationModule,
				groupDomainModule,
				groupDataModule,
				changeGroupModule,
				teachersListScreenModule,
				timetableModule,
			)
		}
	}
}