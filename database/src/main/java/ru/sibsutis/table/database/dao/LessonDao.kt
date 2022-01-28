package ru.sibsutis.table.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.sibsutis.table.database.entities.LessonEntity
import ru.sibsutis.table.shared.lesson.WeekType

@Dao
interface LessonDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertLessons(lessons: List<LessonEntity>)

	//	2 is equals to WeekType.BOTH
	@Query("SELECT * FROM lessons_table WHERE week = :week OR week = 2")
	suspend fun getLessons(
		week: WeekType
	): List<LessonEntity>

	@Query("DELETE FROM lessons_table")
	suspend fun deleteLessons()
}