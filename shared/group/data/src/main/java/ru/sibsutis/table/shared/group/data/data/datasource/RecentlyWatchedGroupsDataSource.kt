package ru.sibsutis.table.shared.group.data.data.datasource

import ru.sibsutis.table.database.entities.RecentlyWatchedGroupEntity

interface RecentlyWatchedGroupsDataSource {

	suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String>

	suspend fun addGroup(newGroup: RecentlyWatchedGroupEntity)
}