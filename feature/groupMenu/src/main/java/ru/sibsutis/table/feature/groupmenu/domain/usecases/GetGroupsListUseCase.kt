package ru.sibsutis.table.feature.groupmenu.domain.usecases

import ru.sibsutis.table.feature.groupmenu.domain.repository.GroupMenuRepository

class GetGroupsListUseCase(
	private val repository: GroupMenuRepository
) {

	operator fun invoke(filter: String) = repository.getGroups(filter)
}