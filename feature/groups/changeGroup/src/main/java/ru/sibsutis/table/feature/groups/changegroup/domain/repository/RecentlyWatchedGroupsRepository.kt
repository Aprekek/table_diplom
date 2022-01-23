package ru.sibsutis.table.feature.groups.changegroup.domain.repository

import ru.sibsutis.table.feature.groups.changegroup.domain.entities.RecentlyWatchedGroup

interface RecentlyWatchedGroupsRepository {

	suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String>

	suspend fun addGroup(newGroup: RecentlyWatchedGroup)
}