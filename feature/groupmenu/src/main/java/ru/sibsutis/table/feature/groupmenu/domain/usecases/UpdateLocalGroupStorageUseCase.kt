package ru.sibsutis.table.feature.groupmenu.domain.usecases

import ru.sibsutis.table.feature.groupmenu.domain.repository.GroupMenuRepository

class UpdateLocalGroupStorageUseCase(
	private val repository: GroupMenuRepository
) {

	suspend operator fun invoke() {
		repository.updateLocalStorageWithRemoteData()
	}
}