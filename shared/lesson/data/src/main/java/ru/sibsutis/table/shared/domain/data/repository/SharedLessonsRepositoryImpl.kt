package ru.sibsutis.table.shared.domain.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sibsutis.table.shared.domain.data.datasource.SharedLessonDataSource
import ru.sibsutis.table.shared.lesson.domain.repository.SharedLessonsRepository

class SharedLessonsRepositoryImpl(
	private val dataSource: SharedLessonDataSource
) : SharedLessonsRepository {

	override suspend fun cleanLessonsStorage() {
		withContext(Dispatchers.IO) {
			dataSource.clearLessonsStorage()
		}
	}
}