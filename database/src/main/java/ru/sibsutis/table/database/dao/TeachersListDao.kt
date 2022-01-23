package ru.sibsutis.table.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.entities.TeacherEntity

@Dao
interface TeachersListDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertTeachers(groups: List<TeacherEntity>)

	@Query("DELETE FROM teachers_table")
	suspend fun deleteAll()

	@Query("SELECT * FROM teachers_table WHERE name LIKE '%' || :searchText || '%'")
	fun getTeachersList(searchText: String = ""): Flow<List<TeacherEntity>>

	@Transaction
	suspend fun replaceOldDataWithNewData(groups: List<TeacherEntity>) {
		deleteAll()
		insertTeachers(groups)
	}
}