package ru.sibsutis.table.shared.group.domain.usecases

import ru.sibsutis.table.shared.group.domain.repository.RecentlyWatchedGroupsRepository

class GetRecentlyWatchedGroupsUseCase(
	private val repository: RecentlyWatchedGroupsRepository
) {

	suspend operator fun invoke(exceptGroup: String) =
		repository.getRecentlyWatchedGroups(exceptGroup)
}