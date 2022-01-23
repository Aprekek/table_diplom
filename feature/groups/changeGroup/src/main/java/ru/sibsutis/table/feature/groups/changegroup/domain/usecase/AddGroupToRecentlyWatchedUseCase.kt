package ru.sibsutis.table.feature.groups.changegroup.domain.usecase

import ru.sibsutis.table.feature.groups.changegroup.domain.entities.RecentlyWatchedGroup
import ru.sibsutis.table.feature.groups.changegroup.domain.repository.RecentlyWatchedGroupsRepository

class AddGroupToRecentlyWatchedUseCase(
	private val repository: RecentlyWatchedGroupsRepository
) {

	suspend operator fun invoke(newGroup: RecentlyWatchedGroup) =
		repository.addGroup(newGroup)
}