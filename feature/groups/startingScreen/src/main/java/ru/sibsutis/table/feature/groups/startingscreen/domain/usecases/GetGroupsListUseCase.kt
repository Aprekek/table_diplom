package ru.sibsutis.table.feature.groups.startingscreen.domain.usecases

import ru.sibsutis.table.feature.groups.startingscreen.domain.repository.GroupMenuRepository

class GetGroupsListUseCase(
	private val repository: GroupMenuRepository
) {

	operator fun invoke(filter: String) = repository.getGroups(filter)
}