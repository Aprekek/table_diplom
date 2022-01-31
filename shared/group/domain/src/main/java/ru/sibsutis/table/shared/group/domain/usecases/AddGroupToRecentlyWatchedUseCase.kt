package ru.sibsutis.table.shared.group.domain.usecases

import ru.sibsutis.table.shared.group.domain.repository.RecentlyWatchedGroupsRepository

class AddGroupToRecentlyWatchedUseCase(
	private val repository: RecentlyWatchedGroupsRepository
) {

	suspend operator fun invoke(newGroup: String) =
		repository.addGroup(newGroup)
}