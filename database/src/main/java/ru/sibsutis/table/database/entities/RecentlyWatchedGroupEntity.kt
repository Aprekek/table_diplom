package ru.sibsutis.table.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "recently_watched_entities_table"
)
data class RecentlyWatchedGroupEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val groupName: String
)