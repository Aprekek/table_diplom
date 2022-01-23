package ru.sibsutis.table.shared.group.domain.usecases

import ru.sibsutis.table.shared.group.domain.repository.GroupMenuRepository

class UpdateLocalGroupStorageUseCase(
	private val repository: GroupMenuRepository
) {

	suspend operator fun invoke() {
		repository.updateLocalStorageWithRemoteData()
	}
}