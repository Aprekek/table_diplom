package ru.sibsutis.table.feature.timetable.data.model

import ru.sibsutis.table.shared.lesson.LessonType
import ru.sibsutis.table.shared.lesson.WeekType

data class LessonModel(
	val id: Long = 0,
	val name: String,
	val day: String,
	val startTime: String,
	val endTime: String,
	val teacher: Long,
	val address: String,
	val type: LessonType,
	val week: WeekType
)