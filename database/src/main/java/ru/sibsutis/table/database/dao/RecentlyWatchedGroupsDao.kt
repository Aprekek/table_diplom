package ru.sibsutis.table.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.sibsutis.table.database.entities.RecentlyWatchedGroupEntity

@Dao
interface RecentlyWatchedGroupsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertGroup(group: RecentlyWatchedGroupEntity)

	@Query("SELECT groupName FROM recently_watched_entities_table WHERE groupName != :exceptGroup ORDER BY id DESC")
	suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String>

	@Query("SELECT * FROM recently_watched_entities_table ORDER BY id DESC")
	suspend fun getAllGroups(): List<RecentlyWatchedGroupEntity>

	@Delete
	suspend fun deleteGroup(group: RecentlyWatchedGroupEntity)

	suspend fun addGroup(newGroup: RecentlyWatchedGroupEntity) {
		val maxGroupSize = 4

		val groups = getAllGroups()
		if (groups.size >= maxGroupSize) {
			val lessImportantGroup = groups.last()
			deleteLessImpAndInsertNewGroups(
				toDelete = lessImportantGroup,
				toInsert = newGroup
			)
		} else {
			insertGroup(newGroup)
		}
	}

	@Transaction
	private suspend fun deleteLessImpAndInsertNewGroups(
		toDelete: RecentlyWatchedGroupEntity,
		toInsert: RecentlyWatchedGroupEntity
	) {
		deleteGroup(toDelete)
		insertGroup(toInsert)
	}
}