package ru.sibsutis.table.feature.groups.startingscreen.domain.usecases

import ru.sibsutis.table.feature.groups.startingscreen.domain.repository.GroupMenuRepository

class IsGroupExistUseCase(
	private val repository: GroupMenuRepository
) {

	suspend operator fun invoke(filter: String) = !repository.isGroupExist(filter).isNullOrBlank()
}