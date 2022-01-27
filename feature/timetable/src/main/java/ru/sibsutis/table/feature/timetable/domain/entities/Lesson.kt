package ru.sibsutis.table.feature.timetable.domain.entities

import ru.sibsutis.table.shared.lesson.Day
import ru.sibsutis.table.shared.lesson.LessonType
import ru.sibsutis.table.shared.lesson.WeekType

data class Lesson(
	val id: Long = 0,
	val name: String,
	val day: Day,
	val startTime: String,
	val endTime: String,
	val teacher: Long,
	val address: String,
	val type: LessonType,
	val week: WeekType
)