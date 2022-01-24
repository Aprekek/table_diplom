package ru.sibsutis.table.features.teachers.listscreen.domain.usecase

import ru.sibsutis.table.features.teachers.listscreen.domain.repository.TeachersListRepository

class UpdateLocalTeachersStorageUseCase(
	private val repository: TeachersListRepository
) {

	suspend operator fun invoke() {
		repository.updateLocalStorageWithRemoteData()
	}
}