package ru.sibsutis.table.feature.groups.changegroup.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sibsutis.table.feature.groups.changegroup.data.datasource.RecentlyWatchedGroupsDataSource
import ru.sibsutis.table.feature.groups.changegroup.data.mappers.toDatabaseEntity
import ru.sibsutis.table.feature.groups.changegroup.domain.entities.RecentlyWatchedGroup
import ru.sibsutis.table.feature.groups.changegroup.domain.repository.RecentlyWatchedGroupsRepository

class RecentlyWatchedGroupsRepositoryImpl(
	private val dataSource: RecentlyWatchedGroupsDataSource
) : RecentlyWatchedGroupsRepository {

	override suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String> =
		withContext(Dispatchers.IO) {
			dataSource.getRecentlyWatchedGroups(exceptGroup)
		}

	override suspend fun addGroup(newGroup: RecentlyWatchedGroup) {
		withContext(Dispatchers.IO) {
			dataSource.addGroup(newGroup.toDatabaseEntity())
		}
	}
}