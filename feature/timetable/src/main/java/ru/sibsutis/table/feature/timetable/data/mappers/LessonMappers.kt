package ru.sibsutis.table.feature.timetable.data.mappers

import ru.sibsutis.table.database.entities.LessonEntity
import ru.sibsutis.table.feature.timetable.data.model.LessonModel
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson
import ru.sibsutis.table.shared.lesson.Day

fun LessonModel.toDatabaseEntity(days: Array<Day>) = LessonEntity(
	id,
	name,
	days.find { it.localizedValue == day } ?: throw IllegalStateException("Unexpected day $day"),
	startTime,
	endTime,
	teacher,
	address,
	type,
	week
)

fun LessonEntity.toEntity() = Lesson(id, name, day, startTime, endTime, teacher, address, type, week)

fun List<LessonModel>.toDatabaseEntity(days: Array<Day>) = map { it.toDatabaseEntity(days) }

fun List<LessonEntity>.toEntity() = map(LessonEntity::toEntity)