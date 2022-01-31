package ru.sibsutis.table.shared.group.domain.repository

interface RecentlyWatchedGroupsRepository {

	suspend fun getRecentlyWatchedGroups(exceptGroup: String): List<String>

	suspend fun addGroup(newGroup: String)
}