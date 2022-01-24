package ru.sibsutis.table.feature.groups.changegroup.domain.repository

interface RecentlyWatchedGroupsRepository {

	suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String>

	suspend fun addGroup(newGroup: String)
}