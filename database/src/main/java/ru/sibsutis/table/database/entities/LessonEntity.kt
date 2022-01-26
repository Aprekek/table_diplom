package ru.sibsutis.table.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.sibsutis.table.shared.lesson.Day
import ru.sibsutis.table.shared.lesson.LessonType
import ru.sibsutis.table.shared.lesson.WeekType

@Entity(
	tableName = "lessons_table"
)
data class LessonEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val name: String,
	val day: Day,
	val startTime: String,
	val endTime: String,
	@ColumnInfo(index = true)
	val teacher: Long,
	val address: String,
	val type: LessonType,
	val week: WeekType
)