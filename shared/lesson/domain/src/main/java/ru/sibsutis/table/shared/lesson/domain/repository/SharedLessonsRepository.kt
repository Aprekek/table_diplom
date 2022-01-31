package ru.sibsutis.table.shared.lesson.domain.repository

interface SharedLessonsRepository {

	suspend fun cleanLessonsStorage()
}