package ru.sibsutis.table.database.dao

import androidx.room.Dao
import androidx.room.Query
import ru.sibsutis.table.database.entities.TeacherEntity

@Dao
interface TeacherDetailsDao {

	@Query("SELECT * FROM teachers_table WHERE name = :name")
	suspend fun getTeacher(name: String): TeacherEntity
}