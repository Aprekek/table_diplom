package ru.sibsutis.table.shared.lesson.domain.usecases

import ru.sibsutis.table.shared.lesson.domain.repository.SharedLessonsRepository

class CleanLessonsStorageUseCase(
	private val repository: SharedLessonsRepository
) {

	suspend operator fun invoke() = repository.cleanLessonsStorage()
}