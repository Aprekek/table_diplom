package ru.sibsutis.table.feature.groups.changegroup.domain.usecase

import ru.sibsutis.table.feature.groups.changegroup.domain.repository.RecentlyWatchedGroupsRepository

class GetRecentlyWatchedGroupsUseCase(
	private val repository: RecentlyWatchedGroupsRepository
) {

	suspend operator fun invoke(exceptGroup: String) =
		repository.getRecentlyWatchedGroups(exceptGroup)
}