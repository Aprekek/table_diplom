package ru.sibsutis.table.shared.domain.data.datasource

interface SharedLessonDataSource {

	suspend fun clearLessonsStorage()
}