package ru.sibsutis.table.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "groups_table"
)
data class GroupEntity(
	@PrimaryKey(autoGenerate = false)
	val name: String
)