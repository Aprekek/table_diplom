package ru.sibsutis.table.shared.group.data.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sibsutis.table.database.entities.RecentlyWatchedGroupEntity
import ru.sibsutis.table.shared.group.data.data.datasource.RecentlyWatchedGroupsDataSource
import ru.sibsutis.table.shared.group.domain.repository.RecentlyWatchedGroupsRepository

class RecentlyWatchedGroupsRepositoryImpl(
	private val dataSource: RecentlyWatchedGroupsDataSource
) : RecentlyWatchedGroupsRepository {

	override suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String> =
		withContext(Dispatchers.IO) {
			dataSource.getRecentlyWatchedGroups(exceptGroup)
		}

	override suspend fun addGroup(newGroup: String) {
		withContext(Dispatchers.IO) {
			dataSource.addGroup(RecentlyWatchedGroupEntity(groupName = newGroup))
		}
	}
}