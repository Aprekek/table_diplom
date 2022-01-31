package ru.sibsutis.table.feature.timetable.data.datasource

import ru.sibsutis.table.database.dao.LessonDao
import ru.sibsutis.table.database.entities.LessonEntity
import ru.sibsutis.table.feature.timetable.data.api.TimetableApi
import ru.sibsutis.table.shared.lesson.domain.entities.WeekType

class TimetableDataSourceImpl(
	private val api: TimetableApi,
	private val dao: LessonDao
) : TimetableDataSource {

	override suspend fun getLessonsForGroupRemote(group: String) = api.getLessonsForGroup(group)

	override suspend fun updateLocalLessonsStorage(lessons: List<LessonEntity>) {
		dao.insertLessons(lessons)
	}

	override suspend fun getTimetable(week: WeekType): List<LessonEntity> = dao.getLessons(week)
}