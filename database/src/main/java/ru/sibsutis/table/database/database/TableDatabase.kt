package ru.sibsutis.table.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.sibsutis.table.database.converters.DayConverter
import ru.sibsutis.table.database.converters.LessonTypeConverter
import ru.sibsutis.table.database.converters.WeekTypeConverter
import ru.sibsutis.table.database.dao.GroupDao
import ru.sibsutis.table.database.dao.LessonDao
import ru.sibsutis.table.database.dao.RecentlyWatchedGroupsDao
import ru.sibsutis.table.database.dao.TeacherDetailsDao
import ru.sibsutis.table.database.dao.TeachersListDao
import ru.sibsutis.table.database.entities.GroupEntity
import ru.sibsutis.table.database.entities.LessonEntity
import ru.sibsutis.table.database.entities.RecentlyWatchedGroupEntity
import ru.sibsutis.table.database.entities.TeacherEntity

@Database(
	entities = [
		GroupEntity::class,
		LessonEntity::class,
		TeacherEntity::class,
		RecentlyWatchedGroupEntity::class,
	],
	version = 1,
	exportSchema = false
)
@TypeConverters(
	LessonTypeConverter::class,
	WeekTypeConverter::class,
	DayConverter::class,
)
abstract class TableDatabase : RoomDatabase() {

	companion object {

		const val DATABASE_NAME = "table_database"
	}

	abstract fun groupDao(): GroupDao
	abstract fun recentlyWatchedGroupsDao(): RecentlyWatchedGroupsDao
	abstract fun teachersListDao(): TeachersListDao
	abstract fun lessonDao(): LessonDao
	abstract fun teachersDetailsDao(): TeacherDetailsDao
}