package ru.sibsutis.table.feature.timetable.data.datasource

import ru.sibsutis.table.database.entities.LessonEntity
import ru.sibsutis.table.feature.timetable.data.model.LessonModel
import ru.sibsutis.table.shared.lesson.WeekType

interface TimetableDataSource {

	suspend fun getLessonsForGroupRemote(group: String): List<LessonModel>

	suspend fun updateLocalLessonsStorage(lessons: List<LessonEntity>)

	suspend fun getTimetable(week: WeekType): List<LessonEntity>
}