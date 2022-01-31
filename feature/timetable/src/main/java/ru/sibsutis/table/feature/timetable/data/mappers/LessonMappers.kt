package ru.sibsutis.table.feature.timetable.data.mappers

import ru.sibsutis.table.database.entities.LessonEntity
import ru.sibsutis.table.feature.timetable.data.model.LessonModel
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson
import ru.sibsutis.table.shared.lesson.domain.entities.Day
import ru.sibsutis.table.shared.lesson.domain.entities.LessonType
import ru.sibsutis.table.shared.lesson.domain.entities.WeekType

fun LessonModel.toDatabaseEntity(
	daysTypes: Array<Day>,
	weeksTypes: Array<WeekType>,
	lessonsTypes: Array<LessonType>
) = LessonEntity(

	name = name,
	day = daysTypes.find { it.localizedValue == day } ?: throw IllegalStateException("Unexpected day $day"),
	startTime = startTime,
	endTime = endTime,
	teacher = teacher,
	address = address,
	type = lessonsTypes[type],
	week = weeksTypes[week]
)

fun LessonEntity.toEntity() = Lesson(id, name, day, startTime, endTime, teacher, address, type, week)

fun List<LessonModel>.toDatabaseEntity(
	days: Array<Day> = Day.values(),
	weeksTypes: Array<WeekType> = WeekType.values(),
	lessonsTypes: Array<LessonType> = LessonType.values()
) = map { it.toDatabaseEntity(days, weeksTypes, lessonsTypes) }

fun List<LessonEntity>.toEntity() = map(LessonEntity::toEntity)