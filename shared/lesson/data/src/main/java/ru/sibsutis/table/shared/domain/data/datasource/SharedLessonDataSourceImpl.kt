package ru.sibsutis.table.shared.domain.data.datasource

import ru.sibsutis.table.database.dao.LessonDao

class SharedLessonDataSourceImpl(
	private val dao: LessonDao
) : SharedLessonDataSource {

	override suspend fun clearLessonsStorage() {
		dao.deleteLessons()
	}
}