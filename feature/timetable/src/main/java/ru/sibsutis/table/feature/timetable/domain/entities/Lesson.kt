package ru.sibsutis.table.feature.timetable.domain.entities

import ru.sibsutis.table.shared.lesson.domain.Day
import ru.sibsutis.table.shared.lesson.domain.LessonType
import ru.sibsutis.table.shared.lesson.domain.WeekType

data class Lesson(
	val id: Long = 0,
	val name: String,
	val day: Day,
	val startTime: String,
	val endTime: String,
	val teacher: String,
	val address: String,
	val type: LessonType,
	val week: WeekType
)