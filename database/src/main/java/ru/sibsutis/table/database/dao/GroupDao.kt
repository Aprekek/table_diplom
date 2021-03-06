package ru.sibsutis.table.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.entities.GroupEntity

@Dao
interface GroupDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertGroups(groups: List<GroupEntity>)

	@Query("SELECT name FROM groups_table WHERE name = :filter")
	fun isGroupExist(filter: String): String?

	@Query("SELECT * FROM groups_table WHERE name LIKE '%' || :filter || '%'")
	fun getGroupsList(filter: String): Flow<List<GroupEntity>>
}