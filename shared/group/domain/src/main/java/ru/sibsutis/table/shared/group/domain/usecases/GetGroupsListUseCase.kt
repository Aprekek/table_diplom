package ru.sibsutis.table.shared.group.domain.usecases

import ru.sibsutis.table.shared.group.domain.repository.GroupMenuRepository

class GetGroupsListUseCase(
	private val repository: GroupMenuRepository
) {

	operator fun invoke(filter: String) = repository.getGroups(filter)
}