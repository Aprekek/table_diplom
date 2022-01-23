package ru.sibsutis.table.feature.groups.changegroup.data.datasource

import ru.sibsutis.table.database.dao.RecentlyWatchedGroupsDao
import ru.sibsutis.table.database.entities.RecentlyWatchedGroupEntity

class RecentlyWatchedGroupsDataSourceImpl(
	private val dao: RecentlyWatchedGroupsDao
) : RecentlyWatchedGroupsDataSource {

	override suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String> =
		dao.getRecentlyWatchedGroups(exceptGroup)

	override suspend fun addGroup(newGroup: RecentlyWatchedGroupEntity) =
		dao.addGroup(newGroup)
}