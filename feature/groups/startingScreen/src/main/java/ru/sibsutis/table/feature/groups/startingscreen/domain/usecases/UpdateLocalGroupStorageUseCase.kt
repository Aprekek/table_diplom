package ru.sibsutis.table.feature.groups.startingscreen.domain.usecases

import ru.sibsutis.table.feature.groups.startingscreen.domain.repository.GroupMenuRepository

class UpdateLocalGroupStorageUseCase(
	private val repository: GroupMenuRepository
) {

	suspend operator fun invoke() {
		repository.updateLocalStorageWithRemoteData()
	}
}