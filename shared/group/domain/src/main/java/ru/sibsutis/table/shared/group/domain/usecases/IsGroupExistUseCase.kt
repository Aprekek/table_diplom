package ru.sibsutis.table.shared.group.domain.usecases

import ru.sibsutis.table.shared.group.domain.repository.GroupMenuRepository

class IsGroupExistUseCase(
	private val repository: GroupMenuRepository
) {

	suspend operator fun invoke(filter: String) = !repository.isGroupExist(filter).isNullOrBlank()
}