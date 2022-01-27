package ru.sibsutis.table.database.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.sibsutis.table.database.database.TableDatabase

val databaseModule = module {

	single {
		Room.databaseBuilder(
			androidContext(),
			TableDatabase::class.java,
			TableDatabase.DATABASE_NAME
		)
			.fallbackToDestructiveMigration()
			.build()
	}

	factory { get<TableDatabase>().groupDao() }
	factory { get<TableDatabase>().recentlyWatchedGroupsDao() }
	factory { get<TableDatabase>().teachersListDao() }
	factory { get<TableDatabase>().lessonDao() }
}